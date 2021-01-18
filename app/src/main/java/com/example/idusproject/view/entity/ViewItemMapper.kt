package com.example.idusproject.view.entity

import com.example.idusproject.model.remote.entity.LocationDetailEntity

object ViewItemMapper {

    fun mapperViewItemEntity(item: LocationDetailEntity?, type: LineType): RecyclerViewItemEntity {
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

    fun mapperIteratorToList(item: Iterator<LocationDetailEntity>): List<RecyclerViewItemEntity> {
        return ArrayList<RecyclerViewItemEntity>().apply {
            this.add(mapperViewItemEntity(null, LineType.TITLE))
            item.forEach {
                this.add(mapperViewItemEntity(it, LineType.CONTENTS))
            }
        }


    }

}