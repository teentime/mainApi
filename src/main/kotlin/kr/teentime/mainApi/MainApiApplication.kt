package kr.teentime.mainApi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MainApiApplication

fun main(args: Array<String>) {
	runApplication<MainApiApplication>(*args)
}
