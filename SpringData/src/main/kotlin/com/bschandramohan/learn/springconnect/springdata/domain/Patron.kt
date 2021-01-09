package com.bschandramohan.learn.springconnect.springdata.domain

import org.hibernate.annotations.GenericGenerator
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

/**
 * User who supports humanity by sharing useful/entertaining links. Stored in PostGres database
 *
 * Can't there be namespaces? Tried to create the table as User and that's not allowed in PostgreSQL
 * https://stackoverflow.com/a/49573003/207552
 *
 * NOTE. For @GeneratedValue, we cannot use the below:
 *    @GeneratedValue(strategy = GenerationType.AUTO)
 *    @GeneratedValue(strategy = GenerationType.SEQUENCE)
 * Results in:
 * org.hibernate.id.IdentifierGenerationException: Unknown integral data type for ids : java.lang.String
 */
@Entity
@Table(name = "patron")
data class Patron(
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    var id: String?,
    var name: String?,
    var email: String?,
    var phoneNumber: Number? // Not handling +1, * etc., to keep it simple and use Number.
)
