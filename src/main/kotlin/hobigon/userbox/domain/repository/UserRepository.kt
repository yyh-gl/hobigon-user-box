package hobigon.userbox.domain.repository

import hobigon.userbox.domain.entity.user.User

interface UserRepository {
    fun save(user: User)
}
