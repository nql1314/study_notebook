# Mogondb
http://www.runoob.com/mongodb/mongodb-tutorial.html
* nosql 分布式 无事务
* database collection document field index primary key
* 数据类型:String,Integer,Boolean,Double,Min/Max keys,Array,Timestamp,Object,Null,
Symbol,Date,Object ID,Binary Data,Code,Regular expression

## 操作
* 创建数据库:use DATABASE_NAME
* 删除数据库:db.dropDatabase()
* 创建集合:db.createCollection(name, options)
* 删除集合:db.collection.drop()
* 插入文档:db.COLLECTION_NAME.insert(document)
* 更新文档:db.collection.update( <query>, <update>,{upsert: <boolean>, multi: <boolean>,
            writeConcern: <document>})
* 替换文档:db.collection.save(<document>,{writeConcern: <document> })
* 删除文档:db.collection.remove(<query>, {justOne: <boolean>,  writeConcern: <document>})
* 查询文档:db.collection.find(query, projection)
    * 等于	{<key>:<value>}
    * 小于	{<key>:{$lt:<value>}}	
    * 小于或等于	{<key>:{$lte:<value>}}
    * 大于	{<key>:{$gt:<value>}}	
    * 大于或等于	{<key>:{$gte:<value>}}	
    * 不等于	{<key>:{$ne:<value>}}
    * AND db.col.find({key1:value1, key2:value2}).pretty()
    * OR db.col.find({ $or: [{key1: value1}, {key2:value2}]}).pretty()
    * db.col.find({"title" : {$type : 2}})
    * db.col.find({"title" : {$type : 'string'}})
    * db.COLLECTION_NAME.find().limit(NUMBER)
    * db.COLLECTION_NAME.find().limit(NUMBER).skip(NUMBER)
    * db.COLLECTION_NAME.find().sort({KEY:1})
    * db.COLLECTION_NAME.find().sort({KEY:-1})
* 索引 db.collection.createIndex(keys, options) options 1 or -1 升序 or 降序
* 聚合 db.COLLECTION_NAME.aggregate(AGGREGATE_OPERATION)
    * db.mycol.aggregate([{$group : {_id : "$by_user", num_tutorial : {$sum : 1}}}])
## 高级特性
* 嵌入式关系 和 引用式关系
* 查询分析:
    * db.users.find({gender:"M"},{user_name:1,_id:0}).explain()
    * db.users.find({gender:"M"},{user_name:1,_id:0}).hint({gender:1,user_name:1}).explain()
   
    
            