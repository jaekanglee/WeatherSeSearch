package com.example.idusproject.view.entity

import com.example.idusproject.model.remote.entity.WoeidEntity

object ViewItemMapper {

    fun mapperViewItemEntity(item: WoeidEntity?, type: LineType): RecyclerViewItemEntity {
        return when (type) {
            LineType.TITLE -> {
                RecyclerViewItemEntity(
                    null,
                    type,
                    null
                )
            }
            else -> {
                RecyclerViewItemEntity(
                    item?.title,
                    type,
                    item?.consolidated_weather
                )
            }
        }


    }

}