#!/bin/bash

function create_database() {
  local database=$MONGO_INITDB_DATABASE
  echo "  Creating database '$database'"
  mongo <<EOF
  use admin
  db.createUser( {user:"$MONGO_USERNAME", pwd:"$MONGO_PASSWORD", roles:["root"]} )
  use $database
EOF
}
