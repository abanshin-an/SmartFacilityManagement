#!/usr/bin/env bash
pushd jmeter
rm -rf logs && mkdir logs
export JMETER_HOME=/opt/homebrew/Cellar/jmeter/5.6.3/libexec
gradle run