package ru.itmo.spacemarinesservice.repository

import org.hibernate.Session
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.itmo.spacemarinesservice.configuration.DatabaseSessionManager
import ru.itmo.spacemarinesservice.exception.DatabaseInteractionException
import ru.itmo.spacemarinesservice.model.body.Starship

@Component
class StarshipsRepository {

    @Autowired
    private lateinit var databaseSessionManager: DatabaseSessionManager

    fun saveStarship(starship: Starship) {
        val session: Session = databaseSessionManager.getSession()
        try {
            session.transaction

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