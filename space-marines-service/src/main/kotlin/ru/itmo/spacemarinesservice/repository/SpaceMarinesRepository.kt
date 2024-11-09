package ru.itmo.spacemarinesservice.repository

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.hibernate.Session
import ru.itmo.spacemarinesservice.configuration.DatabaseSessionManager
import ru.itmo.spacemarinesservice.exception.DatabaseInteractionException
import ru.itmo.spacemarinesservice.model.entity.AstartesCategory
import ru.itmo.spacemarinesservice.model.entity.QueryParams
import ru.itmo.spacemarinesservice.model.entity.SpaceMarine
import ru.itmo.spacemarinesservice.model.request.PostSpaceMarineRequest
import java.util.*


@ApplicationScoped
class SpaceMarinesRepository {

    @Inject
    private lateinit var databaseSessionManager: DatabaseSessionManager

    fun saveSpaceMarine(spaceMarine: SpaceMarine) {
        val session: Session = databaseSessionManager.getSession()
        try {
            session.beginTransaction()

            session.persist(spaceMarine)

            session.transaction.commit()
        } catch (e: Exception) {
            e.printStackTrace()
            if (session.transaction.isActive) {
                session.transaction.rollback()
            }
            throw DatabaseInteractionException(
                message = "An error occurred while saving SpaceMarine entity",
                cause = e,
            )
        } finally {
            databaseSessionManager.closeSession(session)
        }
    }

    fun getSpaceMarines(queryParams: QueryParams): List<SpaceMarine> {
        val session: Session = databaseSessionManager.getSession()
        try {
            session.beginTransaction()

            return session.createQuery(
                "SELECT y FROM SpaceMarine y ${constructWhereClose(queryParams)} ${constructSortClause(queryParams)}",
                SpaceMarine::class.java,
            )
                .setFirstResult(queryParams.offset ?: 0)
                .setMaxResults(queryParams.limit ?: Int.MAX_VALUE)
                .resultList

        } catch (e: Exception) {
            e.printStackTrace()
            if (session.transaction.isActive) {
                session.transaction.rollback()
            }
            throw DatabaseInteractionException(
                message = "An error occurred while retrieving SpaceMarine entities",
                cause = e,
            )
        } finally {
            if (session.isOpen) {
                databaseSessionManager.closeSession(session)
            }
        }
    }

    fun getSpaceMarine(spaceMarineId: Long): SpaceMarine? {
        val session: Session = databaseSessionManager.getSession()
        try {
            session.beginTransaction()

            return session.createQuery("SELECT y FROM SpaceMarine y WHERE y.id = :id", SpaceMarine::class.java)
                .setParameter("id", spaceMarineId)
                .singleResultOrNull

        } catch (e: Exception) {
            e.printStackTrace()
            if (session.transaction.isActive) {
                session.transaction.rollback()
            }
            throw DatabaseInteractionException(
                message = "An error occurred while retrieving SpaceMarine entity with id = $spaceMarineId",
                cause = e,
            )
        } finally {
            if (session.isOpen) {
                databaseSessionManager.closeSession(session)
            }
        }
    }

    fun updateSpaceMarine(spaceMarine: SpaceMarine) {
        val session: Session = databaseSessionManager.getSession()
        try {
            session.beginTransaction()

            session.merge(spaceMarine)

            session.transaction.commit()
        } catch (e: Exception) {
            e.printStackTrace()
            if (session.transaction.isActive) {
                session.transaction.rollback()
            }
            throw DatabaseInteractionException(
                message = "An error occurred while updating SpaceMarine entity with id = ${spaceMarine.id}",
                cause = e,
            )
        } finally {
            if (session.isOpen) {
                databaseSessionManager.closeSession(session)
            }
        }
    }

    fun deleteSpaceMarineById(spaceMarineId: Long): Int {
        val session: Session = databaseSessionManager.getSession()
        try {
            session.beginTransaction()

            return session
                .createMutationQuery("DELETE FROM SpaceMarine y WHERE y.id = :id")
                .setParameter("id", spaceMarineId)
                .executeUpdate()

        } catch (e: Exception) {
            e.printStackTrace()
            if (session.transaction.isActive) {
                session.transaction.rollback()
            }
            throw DatabaseInteractionException(
                message = "An error occurred while deleting SpaceMarine entity with id = $spaceMarineId",
                cause = e,
            )
        } finally {
            if (session.isOpen) {
                databaseSessionManager.closeSession(session)
            }
        }
    }

    fun deleteSpaceMarineByCategory(category: AstartesCategory): Int {
        val session: Session = databaseSessionManager.getSession()
        try {
            session.beginTransaction()

            return session
                .createNativeMutationQuery("DELETE FROM spacemarines y WHERE y.id = (SELECT x.id FROM spacemarines x WHERE x.category = :category LIMIT 1)")
                .setParameter("category", category.name)
                .executeUpdate()

        } catch (e: Exception) {
            e.printStackTrace()
            if (session.transaction.isActive) {
                session.transaction.rollback()
            }
            throw DatabaseInteractionException(
                message = "An error occurred while deleting SpaceMarine entity with category = ${category.name}",
                cause = e,
            )
        } finally {
            if (session.isOpen) {
                databaseSessionManager.closeSession(session)
            }
        }
    }

    fun getAnyLoyalSpaceMarine(): SpaceMarine? {
        val session: Session = databaseSessionManager.getSession()
        try {
            session.beginTransaction()

            return session
                .createQuery("SELECT y FROM SpaceMarine y WHERE loyal = true", SpaceMarine::class.java)
                .setMaxResults(1)
                .singleResultOrNull

        } catch (e: Exception) {
            e.printStackTrace()
            if (session.transaction.isActive) {
                session.transaction.rollback()
            }
            throw DatabaseInteractionException(
                message = "An error occurred while retrieving SpaceMarine entities with loyal = true",
                cause = e,
            )
        } finally {
            if (session.isOpen) {
                databaseSessionManager.closeSession(session)
            }
        }
    }

    fun getAmountOfHealthySpaceMarines(minHealth: Float): Long {
        val session: Session = databaseSessionManager.getSession()
        try {
            session.beginTransaction()

            return session
                .createQuery("SELECT COUNT(y) FROM SpaceMarine y WHERE y.health >= :minHealth", Long::class.java)
                .setParameter("minHealth", minHealth)
                .singleResultOrNull

        } catch (e: Exception) {
            e.printStackTrace()
            if (session.transaction.isActive) {
                session.transaction.rollback()
            }
            throw DatabaseInteractionException(
                message = "An error occurred while retrieving an amount of SpaceMarine entities with health >= $minHealth",
                cause = e,
            )
        } finally {
            if (session.isOpen) {
                databaseSessionManager.closeSession(session)
            }
        }
    }

    fun addToStarship(spaceMarineId: Long, starshipId: UUID): Int {
        val session: Session = databaseSessionManager.getSession()
        try {
            session.beginTransaction()

            return session.createNativeMutationQuery("UPDATE spacemarines SET starship_id = :starshipId WHERE id = :id")
                .setParameter("id", spaceMarineId)
                .setParameter("starshipId", starshipId)
                .executeUpdate()

        } catch (e: Exception) {
            e.printStackTrace()
            if (session.transaction.isActive) {
                session.transaction.rollback()
            }
            throw DatabaseInteractionException(
                message = "An error occurred while setting starship_id = $starshipId to SpaceMarine with id = $spaceMarineId",
                cause = e,
            )
        } finally {
            if (session.isOpen) {
                databaseSessionManager.closeSession(session)
            }
        }
    }

    private fun constructWhereClose(queryParams: QueryParams): String {
        var where = "WHERE 1 = 1"

        if (queryParams.minId != null) {
            where += " and y.id >= ${queryParams.minId}"
        }

        if (queryParams.maxId != null) {
            where += " and y.id <= ${queryParams.maxId}"
        }

        if (queryParams.name != null) {
            where += " and y.name LIKE '${queryParams.name}'"
        }

        if (queryParams.minX != null) {
            where += " and y.coordinates.x >= ${queryParams.minX}"
        }

        if (queryParams.maxX != null) {
            where += " and y.coordinates.x <= ${queryParams.maxX}"
        }

        if (queryParams.minY != null) {
            where += " and y.coordinates.y >= ${queryParams.minY}"
        }

        if (queryParams.maxY != null) {
            where += " and y.coordinates.y <= ${queryParams.maxY}"
        }

        if (queryParams.minCreationDate != null) {
            where += " and y.creationDate >= '${queryParams.minCreationDate.toString()}'"
        }

        if (queryParams.maxCreationDate != null) {
            where += " and y.creationDate <= '${queryParams.maxCreationDate.toString()}'"
        }

        if (queryParams.minHealth != null) {
            where += " and y.health >= ${queryParams.minHealth}"
        }

        if (queryParams.maxHealth != null) {
            where += " and y.health <= ${queryParams.maxHealth}"
        }

        if (queryParams.loyal != null) {
            where += " and y.loyal = ${queryParams.loyal}"
        }

        if (queryParams.minHeight != null) {
            where += " and y.height >= ${queryParams.minHeight}"
        }

        if (queryParams.maxHeight != null) {
            where += " and y.height <= ${queryParams.maxHeight}"
        }

        if (queryParams.category != null) {
            where += " and y.category = '${queryParams.category!!.name}'"
        }

        if (queryParams.chapterName != null) {
            where += " and y.chapter.name LIKE '${queryParams.chapterName}'"
        }

        if (queryParams.chapterWorld != null) {
            where += " and y.chapter.world LIKE '${queryParams.chapterWorld}'"
        }

        return where
    }

    private fun constructSortClause(queryParams: QueryParams): String {
        var sort = ""

        if (queryParams.sortBy != null && queryParams.sortBy!!.isNotEmpty()) {
            sort = " ORDER BY"
            queryParams.sortBy!!.forEachIndexed { i, elem ->
                sort += " y.${elem.fieldName}"
                if (queryParams.sortDirection != null) {
                    sort += " ${queryParams.sortDirection!!.name.lowercase(Locale.getDefault())}"
                }
                if (i != queryParams.sortBy!!.size - 1) {
                    sort += ","
                }
            }
        }

        return sort
    }

    private fun constructPaging(queryParams: QueryParams): String {
        var paging = ""

        if (queryParams.limit != null) {
            paging += " LIMIT ${queryParams.limit}"
        }

        if (queryParams.offset != null) {
            paging += " OFFSET ${queryParams.offset}"
        }

        return paging
    }
}