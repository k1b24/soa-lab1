package ru.itmo.spacemarinesservice.repository

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.hibernate.Session
import ru.itmo.spacemarinesservice.configuration.DatabaseSessionManager
import ru.itmo.spacemarinesservice.exception.DatabaseInteractionException
import ru.itmo.spacemarinesservice.model.entity.Starship

@ApplicationScoped
class StarshipsRepository {

    @Inject
    private lateinit var databaseSessionManager: DatabaseSessionManager

    fun saveStarship(starship: Starship) {
        val session: Session = databaseSessionManager.getSession()
        try {
            session.beginTransaction()

            session.persist(starship)

            session.transaction.commit()
        } catch (e: Exception) {
            e.printStackTrace()
            if (session.transaction.isActive) {
                session.transaction.rollback()
            }
            throw DatabaseInteractionException(
                message = "An error occurred while saving Starship entity",
                cause = e,
            )
        } finally {
            databaseSessionManager.closeSession(session)
        }
    }
}