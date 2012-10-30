databaseChangeLog = {

	changeSet(author: "davidortiz (generated)", id: "1350566373485-1") {
		createTable(tableName: "MACHINE") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_5")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "CERTIFICATE", type: "VARCHAR(255)")

			column(name: "LAST_PATIENT_COUNT", type: "INT")

			column(name: "LAST_RESPONSE_TIME_IN_MILLIS", type: "BIGINT")

			column(name: "NAME", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "REAL_NAME", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "STATUS", type: "VARCHAR(255)")

			column(name: "URL", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "davidortiz (generated)", id: "1350566373485-2") {
		createTable(tableName: "PERMISSION") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_F")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "ALLOW_PDO", type: "BOOLEAN") {
				constraints(nullable: "false")
			}

			column(name: "ALLOW_SITE_IDENTIFY", type: "BOOLEAN") {
				constraints(nullable: "false")
			}

			column(name: "STUDY_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "USER_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "davidortiz (generated)", id: "1350566373485-3") {
		createTable(tableName: "PREFERENCE") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_C")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "HEART_BEAT_STUDY_ID", type: "BIGINT")

			column(name: "I2B2ONT_CELL", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "SHRINE_CELL", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "davidortiz (generated)", id: "1350566373485-4") {
		createTable(tableName: "QUERY_SESSION") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_2")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "CREATED", type: "TIMESTAMP") {
				constraints(nullable: "false")
			}

			column(name: "SESSION_ID", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "USER_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "davidortiz (generated)", id: "1350566373485-5") {
		createTable(tableName: "STUDY") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_4")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "STUDY_DESCRIPTION", type: "VARCHAR(255)")

			column(name: "STUDY_NAME", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "davidortiz (generated)", id: "1350566373485-6") {
		createTable(tableName: "STUDY_MACHINES") {
			column(name: "MACHINE_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "STUDY_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "davidortiz (generated)", id: "1350566373485-7") {
		createTable(tableName: "USER") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_27")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "INSTITUTION_NAME", type: "VARCHAR(255)")

			column(name: "IS_ADMIN", type: "BOOLEAN") {
				constraints(nullable: "false")
			}

			column(name: "IS_SYSTEM_USER", type: "BOOLEAN") {
				constraints(nullable: "false")
			}

			column(name: "REAL_NAME", type: "VARCHAR(255)")

			column(name: "USER_NAME", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "davidortiz (generated)", id: "1350566373485-8") {
		createTable(tableName: "USER_MACHINE") {
			column(name: "USER_HOMESITES_ID", type: "BIGINT")

			column(name: "MACHINE_ID", type: "BIGINT")
		}
	}

	changeSet(author: "davidortiz (generated)", id: "1350566373485-9") {
		addPrimaryKey(columnNames: "STUDY_ID, MACHINE_ID", constraintName: "CONSTRAINT_A", tableName: "STUDY_MACHINES")
	}

	changeSet(author: "davidortiz (generated)", id: "1350566373485-10") {
		addForeignKeyConstraint(baseColumnNames: "STUDY_ID", baseTableName: "PERMISSION", baseTableSchemaName: "PUBLIC", constraintName: "FKE125C5CFCAEA9D74", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "STUDY", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "davidortiz (generated)", id: "1350566373485-11") {
		addForeignKeyConstraint(baseColumnNames: "USER_ID", baseTableName: "PERMISSION", baseTableSchemaName: "PUBLIC", constraintName: "FKE125C5CF8ED0FA40", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "USER", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "davidortiz (generated)", id: "1350566373485-12") {
		addForeignKeyConstraint(baseColumnNames: "HEART_BEAT_STUDY_ID", baseTableName: "PREFERENCE", baseTableSchemaName: "PUBLIC", constraintName: "FKA8FCBCDBEA4F57A4", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "STUDY", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "davidortiz (generated)", id: "1350566373485-13") {
		addForeignKeyConstraint(baseColumnNames: "USER_ID", baseTableName: "QUERY_SESSION", baseTableSchemaName: "PUBLIC", constraintName: "FK6A2DFA1F8ED0FA40", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "USER", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "davidortiz (generated)", id: "1350566373485-14") {
		addForeignKeyConstraint(baseColumnNames: "MACHINE_ID", baseTableName: "STUDY_MACHINES", baseTableSchemaName: "PUBLIC", constraintName: "FKA899DAC2C667634", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "MACHINE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "davidortiz (generated)", id: "1350566373485-15") {
		addForeignKeyConstraint(baseColumnNames: "STUDY_ID", baseTableName: "STUDY_MACHINES", baseTableSchemaName: "PUBLIC", constraintName: "FKA899DAC2CAEA9D74", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "STUDY", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "davidortiz (generated)", id: "1350566373485-16") {
		addForeignKeyConstraint(baseColumnNames: "MACHINE_ID", baseTableName: "USER_MACHINE", baseTableSchemaName: "PUBLIC", constraintName: "FK8C20DBD3C667634", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "MACHINE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "davidortiz (generated)", id: "1350566373485-17") {
		addForeignKeyConstraint(baseColumnNames: "USER_HOMESITES_ID", baseTableName: "USER_MACHINE", baseTableSchemaName: "PUBLIC", constraintName: "FK8C20DBD3AE998E32", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "USER", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "davidortiz (generated)", id: "1350566373485-18") {
		createIndex(indexName: "CONSTRAINT_INDEX_5", tableName: "MACHINE", unique: "true") {
			column(name: "NAME")
		}
	}

	changeSet(author: "davidortiz (generated)", id: "1350566373485-19") {
		createIndex(indexName: "CONSTRAINT_INDEX_F", tableName: "PERMISSION", unique: "true") {
			column(name: "STUDY_ID")

			column(name: "USER_ID")
		}
	}

	changeSet(author: "davidortiz (generated)", id: "1350566373485-20") {
		createIndex(indexName: "CONSTRAINT_INDEX_4", tableName: "STUDY", unique: "true") {
			column(name: "STUDY_NAME")
		}
	}

	changeSet(author: "davidortiz (generated)", id: "1350566373485-21") {
		createIndex(indexName: "CONSTRAINT_INDEX_2", tableName: "USER", unique: "true") {
			column(name: "USER_NAME")
		}
	}
}
