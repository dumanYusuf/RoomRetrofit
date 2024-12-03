package com.dumanyusuf.roomretrofit.data.remote.dto

import com.dumanyusuf.roomretrofit.domain.model.Flag


class FlagDto : ArrayList<FlagDtoItem>()

fun FlagDto.toFlag():List<Flag>{
    return map { Flag(it.name,it.capital,it.region,it.currency,it.flag,it.language) }
}

