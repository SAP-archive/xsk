#!/bin/bash
array=(

######## patterns to look for
"test" "test" "D123123"
 )

DIRNAME="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
SCRIPTNAME=$(basename "$0")
TMPFILE=$(mktemp)
trap "rm -rf $TMPFILE" EXIT
for element in ${array[@]}
do

######## file exceptions
    find $DIRNAME -type f \( \
\
   -iname "*" \
 ! -iname "$SCRIPTNAME" \
 ! -wholename "*/.git/*" \
 ! -wholename "*/shine-light/*" \
 ! -wholename "*/gcc/*" \
 ! -wholename "*/dist/*" \
 ! -wholename "*/gen/*" \
 ! -wholename "*/sapjvm_8_jre/*" \
 ! -wholename "*/node_modules/*" \
\
 \) -print0 | xargs -0 grep -l -i "$element">$TMPFILE
done

if [ -s $TMPFILE ]
then
    echo Found names or forbidden strings in source files. Configure the forbidden strings and exceptions in $DIRNAME/$SCRIPTNAME.
    cat $TMPFILE
    exit 1
fi
