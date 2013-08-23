databaseChangeLog = {

	changeSet(author: "davidortiz (generated)", id: "1364411514186-1") {
		createTable(tableName: "machine") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "machinePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "certificate", type: "varchar(255)")

			column(name: "endpoint_status", type: "varchar(255)")

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "real_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "url", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "davidortiz (generated)", id: "1364411514186-2") {
		createTable(tableName: "permission") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "permissionPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "allow_pdo", type: "boolean") {
				constraints(nullable: "false")
			}

			column(name: "allow_site_identify", type: "boolean") {
				constraints(nullable: "false")
			}

			column(name: "study_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "davidortiz (generated)", id: "1364411514186-3") {
		createTable(tableName: "preference") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "preferencePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "heart_beat_study_id", type: "bigint")

			column(name: "i2b2ont_cell", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "shrine_cell", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "davidortiz (generated)", id: "1364411514186-4") {
		createTable(tableName: "query_session") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "query_sessionPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "created", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "session_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "davidortiz (generated)", id: "1364411514186-5") {
		createTable(tableName: "status") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "statusPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "machine_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "number_of_patients", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "response_time_in_millis", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "time_stamp", type: "timestamp") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "davidortiz (generated)", id: "1364411514186-6") {
		createTable(tableName: "study") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "studyPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "study_description", type: "varchar(255)")

			column(name: "study_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "davidortiz (generated)", id: "1364411514186-7") {
		createTable(tableName: "study_machines") {
			column(name: "machine_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "study_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "davidortiz (generated)", id: "1364411514186-8") {
		createTable(tableName: "user") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "userPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "institution_name", type: "varchar(255)")

			column(name: "is_admin", type: "boolean") {
				constraints(nullable: "false")
			}

			column(name: "is_system_user", type: "boolean") {
				constraints(nullable: "false")
			}

			column(name: "real_name", type: "varchar(255)")

			column(name: "user_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "davidortiz (generated)", id: "1364411514186-9") {
		createTable(tableName: "user_machine") {
			column(name: "user_homesites_id", type: "bigint")

			column(name: "machine_id", type: "bigint")
		}
	}

	changeSet(author: "davidortiz (generated)", id: "1364411514186-10") {
		addPrimaryKey(columnNames: "study_id, machine_id", tableName: "study_machines")
	}

	changeSet(author: "davidortiz (generated)", id: "1364411514186-11") {
		addForeignKeyConstraint(baseColumnNames: "study_id", baseTableName: "permission", constraintName: "FKE125C5CFCAEA9D74", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "study", referencesUniqueColumn: "false")
	}

	changeSet(author: "davidortiz (generated)", id: "1364411514186-12") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "permission", constraintName: "FKE125C5CF8ED0FA40", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "davidortiz (generated)", id: "1364411514186-13") {
		addForeignKeyConstraint(baseColumnNames: "heart_beat_study_id", baseTableName: "preference", constraintName: "FKA8FCBCDBEA4F57A4", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "study", referencesUniqueColumn: "false")
	}

	changeSet(author: "davidortiz (generated)", id: "1364411514186-14") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "query_session", constraintName: "FK6A2DFA1F8ED0FA40", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "davidortiz (generated)", id: "1364411514186-15") {
		addForeignKeyConstraint(baseColumnNames: "machine_id", baseTableName: "status", constraintName: "FKCACDCFF2C667634", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "machine", referencesUniqueColumn: "false")
	}

	changeSet(author: "davidortiz (generated)", id: "1364411514186-16") {
		addForeignKeyConstraint(baseColumnNames: "machine_id", baseTableName: "study_machines", constraintName: "FKA899DAC2C667634", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "machine", referencesUniqueColumn: "false")
	}

	changeSet(author: "davidortiz (generated)", id: "1364411514186-17") {
		addForeignKeyConstraint(baseColumnNames: "study_id", baseTableName: "study_machines", constraintName: "FKA899DAC2CAEA9D74", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "study", referencesUniqueColumn: "false")
	}

	changeSet(author: "davidortiz (generated)", id: "1364411514186-18") {
		addForeignKeyConstraint(baseColumnNames: "machine_id", baseTableName: "user_machine", constraintName: "FK8C20DBD3C667634", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "machine", referencesUniqueColumn: "false")
	}

	changeSet(author: "davidortiz (generated)", id: "1364411514186-19") {
		addForeignKeyConstraint(baseColumnNames: "user_homesites_id", baseTableName: "user_machine", constraintName: "FK8C20DBD3AE998E32", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "davidortiz (generated)", id: "1364411514186-20") {
		createIndex(indexName: "name_unique_1364411514110", tableName: "machine", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "davidortiz (generated)", id: "1364411514186-21") {
		createIndex(indexName: "unique-user_id", tableName: "permission") {
			column(name: "study_id")

			column(name: "user_id")
		}
	}

	changeSet(author: "davidortiz (generated)", id: "1364411514186-22") {
		createIndex(indexName: "study_name_unique_1364411514140", tableName: "study", unique: "true") {
			column(name: "study_name")
		}
	}

	changeSet(author: "davidortiz (generated)", id: "1364411514186-23") {
		createIndex(indexName: "user_name_unique_1364411514146", tableName: "user", unique: "true") {
			column(name: "user_name")
		}
	}
}
