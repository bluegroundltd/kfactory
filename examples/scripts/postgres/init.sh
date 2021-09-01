#!/bin/bash

function create_database() {
  local database=$POSTGRES_DB
  echo "  Creating database '$database'"
  psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE DATABASE $database;
EOSQL
}
