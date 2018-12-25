package com.example.jetpack.roomrxjava.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "user_info")
data class UserEntity constructor(
        @PrimaryKey(autoGenerate = true)
        @SerializedName("id") var id: Int
) : Serializable {
    constructor(): this(0)

    @ColumnInfo(name = "name")
    var name: String = ""

    @ColumnInfo(name = "age")
    var age: Int = 0

    @ColumnInfo(name = "sex")
    var sex: String = ""

    override fun toString(): String {
        return "UserEntity(id=$id, name='$name', age=$age, sex='$sex')"
    }
}