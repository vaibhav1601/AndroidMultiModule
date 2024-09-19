package com.example.multimodule.model

import com.example.multimodule.util.toRealmInstant
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId
import java.time.Instant

  class Diary : RealmObject{
    @PrimaryKey
    var _id :ObjectId= ObjectId.invoke()
    var ownerId:String=""
    var  title:String=""
    var mood:String= Mood.Smile.name
    var descripation:String=""
    var images:RealmList<String> = realmListOf()
    var date: RealmInstant = Instant.now().toRealmInstant()

}