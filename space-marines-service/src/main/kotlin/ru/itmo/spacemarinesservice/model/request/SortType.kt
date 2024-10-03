package ru.itmo.spacemarinesservice.model.request

enum class SortType(val fieldName: String) {
    ID("id"),
    NAME("name"),
    CREATION_DATE("creation_date"),
    HEALTH("health"),
    HEIGHT("height"),
    CATEGORY("category"),
}