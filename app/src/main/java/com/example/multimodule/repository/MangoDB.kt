package com.example.multimodule.repository

import com.example.multimodule.model.Diary
import com.example.multimodule.util.Constant
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.sync.SyncConfiguration

object MangoDB : MangoRepository {

    private val app = App.create(Constant.APP_ID)
    private var user = app.currentUser
    private lateinit var realm: Realm


    override fun configureTheRealm() {
        if (user != null) {
            val config = SyncConfiguration.Builder(user!!, setOf(Diary::class))
                .initialSubscriptions { sub ->
                    add(
                        query = sub.query("ownerId==$0 ", user!!.identities),
                        name = "Users Diaries"
                    )
                }

                .waitForInitialRemoteData()
                .build()
            realm = Realm.open(config)

        }

    }
}