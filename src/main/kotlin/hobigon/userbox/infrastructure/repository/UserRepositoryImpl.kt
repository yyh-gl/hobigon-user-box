package hobigon.userbox.infrastructure.repository

import hobigon.userbox.domain.entity.shared.NotFoundException
import hobigon.userbox.domain.entity.user.Email
import hobigon.userbox.domain.entity.user.ID
import hobigon.userbox.domain.entity.user.Password
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
                USERS.USER_NAME,
                USERS.DISPLAY_NAME
            )
            .values(
                user.id.value,
                user.email.value,
                user.password.hash(),
                user.userName,
                user.displayName
            )
            .onDuplicateKeyUpdate()
            .set(USERS.EMAIL, user.email.value)
            .set(USERS.HASHED_PASSWORD, user.password.hash())
            .set(USERS.USER_NAME, user.userName)
            .set(USERS.DISPLAY_NAME, user.displayName)
            .execute()
    }

    override fun fetchByEmail(email: Email): User {
        val record =
            dslContext.select().from(USERS).where(USERS.EMAIL.eq(email.value)).fetchOne()
                ?: throw NotFoundException()

        return User(
            id = ID(record.getValue(USERS.ID)!!),
            email = Email(record.getValue(USERS.EMAIL)!!),
            password =
                Password(
                    hashedValue = record.getValue(USERS.HASHED_PASSWORD)!!,
                ),
            userName = record.getValue(USERS.USER_NAME)!!,
            displayName = record.getValue(USERS.DISPLAY_NAME)!!,
        )
    }
}
