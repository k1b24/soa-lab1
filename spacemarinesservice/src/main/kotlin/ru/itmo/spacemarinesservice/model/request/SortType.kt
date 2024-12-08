package ru.itmo.spacemarinesservice.model.request

enum class SortType(val fieldName: String) {
    ID("id"),
    NAME("name"),
    CREATION_DATE("creationDate"),
    HEALTH("health"),
    HEIGHT("height"),
    CATEGORY("category"),
}