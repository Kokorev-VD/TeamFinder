package com.example.TeamFinder

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication()
class TeamFinderApplication

fun main(args: Array<String>) {
	runApplication<TeamFinderApplication>(*args)
}
