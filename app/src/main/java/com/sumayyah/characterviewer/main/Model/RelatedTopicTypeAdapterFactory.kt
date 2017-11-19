package com.sumayyah.characterviewer.main.Model

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.reflect.TypeToken
import com.google.gson.JsonElement
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.io.IOException


/**
 * Created by sahmed014c on 11/16/17.
 */
class RelatedTopicTypeAdapterFactory : TypeAdapterFactory {
    override fun <T : Any?> create(gson: Gson, type: TypeToken<T>): TypeAdapter<T> {
        val delegate = gson.getDelegateAdapter(this, type)
        val elementAdapter = gson.getAdapter(JsonElement::class.java)

        return object : TypeAdapter<T>() {

            @Throws(IOException::class)
            override fun write(out: JsonWriter, value: T) {
                delegate.write(out, value)
            }

            @Throws(IOException::class)
            override fun read(`in`: JsonReader): T {

                var jsonElement = elementAdapter.read(`in`)

                if (jsonElement.isJsonObject) {
                    val jsonObject = jsonElement.asJsonObject

                    if (jsonObject.has("RelatedTopics") && jsonObject.get("RelatedTopics").isJsonArray) {
                        jsonElement = jsonObject.get("RelatedTopics")
                    }
                }

                return delegate.fromJsonTree(jsonElement)
            }
        }.nullSafe()
    }
}