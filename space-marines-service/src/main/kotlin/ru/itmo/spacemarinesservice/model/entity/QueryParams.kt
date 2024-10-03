package ru.itmo.spacemarinesservice.model.entity

import ru.itmo.spacemarinesservice.model.request.SortDirection
import ru.itmo.spacemarinesservice.model.request.SortType
import java.time.LocalDate

data class QueryParams(
    var limit: Int?,
    var offset: Int?,
    var sortBy: List<SortType>?,
    var sortDirection: SortDirection?,
    var minId: Long?,
    var maxId: Long?,
    var name: String?,
    var minX: Float?,
    var maxX: Float?,
    var minY: Float?,
    var maxY: Float?,
    var minCreationDate: LocalDate?,
    var maxCreationDate: LocalDate?,
    var minHealth: Float?,
    var maxHealth: Float?,
    var loyal: Boolean?,
    var minHeight: Double?,
    var maxHeight: Double?,
    var category: AstartesCategory?,
    var chapterName: String?,
    var chapterWorld: String?
)
