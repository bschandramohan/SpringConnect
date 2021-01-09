package com.bschandramohan.learn.springconnect.springdata

import org.hibernate.annotations.GenericGenerator
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

/**
 * User who supports humanity by sharing useful/entertaining links
 *
 * Can't there be namespaces? Tried to create the table as User and that's not allowed in PostgreSQL
 * https://stackoverflow.com/a/49573003/207552
 */
@Entity
@Table(name = "patron")
data class Patron(
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    var id: String,
    var name: String
)
