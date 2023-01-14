package hobigon.userbox.infrastructure.repository

import hobigon.userbox.domain.entity.user.User
import hobigon.userbox.domain.repository.UserRepository
import hobigon.userbox.infrastructure.jooq.tables.references.USERS
import org.jooq.DSLContext
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(private val dslContext: DSLContext) : UserRepository {
    override fun save(user: User) {
        dslContext
            .insertInto(
                USERS,
                USERS.ID,
                USERS.EMAIL,
                USERS.HASHED_PASSWORD,
                USERS.SALT,
                USERS.USER_NAME,
                USERS.DISPLAY_NAME
            )
            .values(
                user.id.toString(),
                user.email.toString(),
                user.password.hash(),
                user.password.salt,
                user.userName,
                user.displayName
            )
            .onDuplicateKeyUpdate()
            .set(USERS.EMAIL, user.email.toString())
            .set(USERS.HASHED_PASSWORD, user.password.hash())
            .set(USERS.USER_NAME, user.userName)
            .set(USERS.DISPLAY_NAME, user.displayName)
            .execute()
    }
}
