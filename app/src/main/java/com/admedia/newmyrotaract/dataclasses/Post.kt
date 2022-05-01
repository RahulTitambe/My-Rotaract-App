package com.admedia.newmyrotaract.dataclasses

import java.io.Serializable

data class Post (
    var Image : Int,
    var Title : String,
    var Description : String,
    var DateCrated : String,
    var TimeCreated : String
    ) : Serializable