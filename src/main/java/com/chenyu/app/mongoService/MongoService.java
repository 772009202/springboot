package com.chenyu.app.mongoService;

import com.chenyu.app.entity.Person;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenyu
 * @date 2021-10-16
 */
@Component
public class MongoService {

  @Autowired private MongoTemplate mongoTemplate;

  public void save(Person person) {
    mongoTemplate.save(person);
  }

  public void aggregate() {
    LookupOperation lookup =
        LookupOperation.newLookup()
            .from("coordination-feedback")
            .localField("_id")
            .foreignField("taskId")
            .as("feedback");

    List<AggregationOperation> operations = new ArrayList<>();

    operations.add(lookup);
    ProjectionOperation projectionOperation = new ProjectionOperation();

    Aggregation aggregation = Aggregation.newAggregation(operations);

    AggregationResults<BasicDBObject> coordination =
        mongoTemplate.aggregate(aggregation, "coordination", BasicDBObject.class);
  }

  /** 联表查询多字段 */
  public void aggregate2() {
    // 所有操作的集合
    List<Bson> pipeline = new ArrayList<>();

    // 联表查询
    BasicDBObject lookup = new BasicDBObject();

    // 构建联表的join条件
    BasicDBList eqIdList = new BasicDBList();
    eqIdList.add("$taskId");
    eqIdList.add("$$taskId");

    BasicDBList eqPeriodIndexList = new BasicDBList();
    eqPeriodIndexList.add("$periodIndex");
    eqPeriodIndexList.add("$$periodIndex");

    BasicDBList addList = new BasicDBList();
    addList.add(new BasicDBObject("$eq", eqIdList));
    addList.add(new BasicDBObject("$eq", eqPeriodIndexList));

    BasicDBList matchList = new BasicDBList();
    matchList.add(
        new BasicDBObject(
            "$match", new BasicDBObject("$expr", new BasicDBObject("$and", addList))));

    // 构建联表查询结构
    lookup.put(
        "$lookup",
        new BasicDBObject("from", "coordination-feedback")
            .append(
                "let", new BasicDBObject("taskId", "$_id").append("periodIndex", "$periodIndex"))
            .append("pipeline", matchList)
            .append("as", "feedback"));

    // 条件过滤 排序 分页等操作
    BasicDBObject match = new BasicDBObject();
    match.put("$match", new BasicDBObject("businessType", "1"));

    BasicDBObject sort = new BasicDBObject();
    sort.put("$sort", new BasicDBObject("createTime", -1));

    BasicDBObject skip = new BasicDBObject();
    skip.put("$skip", 10);

    BasicDBObject limit = new BasicDBObject();
    limit.put("$limit", 10);

    pipeline.add(lookup);
    pipeline.add(match);
    pipeline.add(sort);
    pipeline.add(skip);
    pipeline.add(limit);

    // 打印数据库操作的json
    System.out.println(pipeline.toString());

    AggregateIterable<Document> coordinationResult =
        mongoTemplate.getCollection("coordination").aggregate(pipeline);
    // 遍历结果集
    MongoCursor<Document> iterator = coordinationResult.iterator();
    while (iterator.hasNext()) {
      Document next = iterator.next();
      System.out.println(next);
    }
  }

  /** 联表查询多字段 */
  public void aggregate3() {
    // 所有操作的集合
    List<Bson> pipeline = new ArrayList<>();

    // 联表查询
    BasicDBObject lookup = new BasicDBObject();

    // 构建联表的join条件
    BasicDBList eqIdList = new BasicDBList();
    eqIdList.add("$taskId");
    eqIdList.add("$$taskId");

    BasicDBList eqPeriodIndexList = new BasicDBList();
    eqPeriodIndexList.add("$periodIndex");
    eqPeriodIndexList.add("$$periodIndex");

    BasicDBList addList = new BasicDBList();
    addList.add(new BasicDBObject("$eq", eqIdList));
    addList.add(new BasicDBObject("$eq", eqPeriodIndexList));

    BasicDBList matchList = new BasicDBList();
    matchList.add(
        new BasicDBObject(
            "$match", new BasicDBObject("$expr", new BasicDBObject("$and", addList))));

    // 构建联表查询结构
    lookup.put(
        "$lookup",
        new BasicDBObject("from", "coordination-feedback")
            .append(
                "let", new BasicDBObject("taskId", "$_id").append("periodIndex", "$periodIndex"))
            .append(
                "pipeline",
                BasicDBListOperation.build(
                    new BasicDBObject(
                        "$match",
                        new BasicDBObject(
                            "$expr",
                            new BasicDBObject(
                                "$and",
                                BasicDBListOperation.build(
                                    new BasicDBObject(
                                        "$eq", BasicDBListOperation.build("$taskId", "$$taskId")),
                                    new BasicDBObject(
                                        "$eq",
                                        BasicDBListOperation.build(
                                            "$periodIndex", "eqPeriodIndexList"))))))))
            .append("as", "feedback"));

    // 条件过滤 排序 分页等操作
    BasicDBObject match = new BasicDBObject();
    match.put("$match", new BasicDBObject("businessType", "1"));

    BasicDBObject sort = new BasicDBObject();
    sort.put("$sort", new BasicDBObject("createTime", -1));

    BasicDBObject skip = new BasicDBObject();
    skip.put("$skip", 10);

    BasicDBObject limit = new BasicDBObject();
    limit.put("$limit", 10);

    pipeline.add(lookup);
    pipeline.add(match);
    pipeline.add(sort);
    pipeline.add(skip);
    pipeline.add(limit);

    // 打印数据库操作的json
    System.out.println(pipeline.toString());

    AggregateIterable<Document> coordinationResult =
        mongoTemplate.getCollection("coordination").aggregate(pipeline);
    // 遍历结果集
    MongoCursor<Document> iterator = coordinationResult.iterator();
    while (iterator.hasNext()) {
      Document next = iterator.next();
      System.out.println(next);
    }
  }

  /** 置顶功能 */
  public void aggregate4() {

    List<AggregationOperation> operations = new ArrayList<>();

    Field[] coordinationFields = Coodrdination.class.getDeclaredFields();
    String[] coordinationFiledNames = new String[coordinationFields.length];

    for (int i = 0; i < coordinationFields.length; i++) {
      coordinationFiledNames[i] = coordinationFields[i].getName();
    }

    ProjectionOperation project =
        Aggregation.project()
            .andExpression("{$indexOfArray:{{'2','3','6'}, '$_id'}}")
            .as("indexSort")
            .andInclude(coordinationFiledNames);
    SortOperation sort = Aggregation.sort(Sort.Direction.DESC, "indexSort");

    operations.add(project);
    operations.add(sort);

    AggregationResults<DBObject> coordination =
        mongoTemplate.aggregate(
            Aggregation.newAggregation(operations), "coordination", DBObject.class);
    List<DBObject> mappedResults = coordination.getMappedResults();
    System.out.println(mappedResults.toString());
  }
}
