package it.univaq.coldchain

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class ColdchainApplication

fun main(args: Array<String>) {
    runApplication<ColdchainApplication>(*args)
}
