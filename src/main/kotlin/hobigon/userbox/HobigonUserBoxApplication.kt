package hobigon.userbox

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication class HobigonUserBoxApplication

fun main(args: Array<String>) {
    @Suppress("SpreadOperator") runApplication<HobigonUserBoxApplication>(*args)
}
