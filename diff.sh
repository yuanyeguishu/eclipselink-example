#!/bin/bash

diff -r ../javaee7-example . | grep -v '/.git/' > diff.log 2>&1

