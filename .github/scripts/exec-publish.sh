exec_publish() {
    local module_name="$1"
    local publish_flag="$2"

    if [ "${publish_flag}" = "true" ]; then
      ./gradlew :${module_name}:publish
    else
      echo "${module_name}: SKIPPED"
    fi
}
