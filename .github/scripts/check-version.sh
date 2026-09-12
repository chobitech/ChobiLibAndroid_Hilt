check_version() {
  local module_name="$1"
  local package_name="$2"

  local version
  version=$(./gradlew -q ":${module_name}:properties"
   #|
#    grep '^version:' |
#    sed 's/version: //')

  echo "$module_name version: $version"

  HTTP_STATUS=$(curl \
    -s \
    -o "/tmp/${module_name}_versions.json" \
    -w "%{http_code}" \
    -H "Accept: application/vnd.github+json" \
    -H "Authorization: Bearer $GITHUB_TOKEN" \
    -H "X-GitHub-Api-Version: 2026-03-10" \
    "https://api.github.com/user/packages/maven/$package_name/versions?per_page=100")

  if [ "$HTTP_STATUS" = "404" ]; then
    echo "${module_name}_publish=true" >> "$GITHUB_OUTPUT"
    return 0
  fi

  if [ "$HTTP_STATUS" != "200" ]; then
    cat "/tmp/${module_name}_versions.json"
    return 1
  fi

  if jq -e --arg version "$version" \
    '.[] | select(.name == $version)' \
    "/tmp/${module_name}_versions.json" > /dev/null; then

    echo "$module_name: $version already exists. SKIP"
    echo "${module_name}_publish=false" >> "$GITHUB_OUTPUT"
  else
    echo "$module_name: $version does not exist. PUBLISH"
    echo "${module_name}_publish=true" >> "$GITHUB_OUTPUT"
  fi
}