package edu.chip.i2b2ssr.admin.data

import java.sql.Timestamp


class QuerySession {
  User user
  String sessionId
  Date created = new Date()

  static belongsTo = [User]

  static constraints = {
    sessionId unique: true
  }
}
