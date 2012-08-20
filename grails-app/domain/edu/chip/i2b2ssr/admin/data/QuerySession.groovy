package edu.chip.i2b2ssr.admin.data


class QuerySession {
  User user
  String sessionId
  Date created = new Date()

  static belongsTo = [User]

  static mapping = {
      sessionId maxSize: 100
  }

  static constraints = {
    sessionId unique: true


  }
}
