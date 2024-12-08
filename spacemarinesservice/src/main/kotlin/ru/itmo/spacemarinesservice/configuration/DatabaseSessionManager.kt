package ru.itmo.spacemarinesservice.configuration

import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import org.hibernate.cfg.Configuration
import org.springframework.stereotype.Component
import ru.itmo.spacemarinesservice.model.body.SpaceMarine
import ru.itmo.spacemarinesservice.model.body.Starship

@Component
class DatabaseSessionManager {

    private var sessionFactory: SessionFactory? = null

    init {
        val configuration: Configuration =
            Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(SpaceMarine::class.java)
                .addAnnotatedClass(Starship::class.java)
        StandardServiceRegistryBuilder()
            .applySettings(configuration.properties).build()
        configuration.buildSessionFactory()
        sessionFactory = configuration.buildSessionFactory()
    }

    fun getSession(): Session = sessionFactory!!.openSession()

    fun closeSession(session: Session) {
        if (session.isOpen) {
            session.close()
        }
    }
}