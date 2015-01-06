package auth

/**
 * Created by FScoward on 15/01/06.
 */
case class Cookie(userSession: String, expires: String, path: String, domain: String)
