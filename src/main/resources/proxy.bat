#!/bin/bash
while read line; do
  curl -s -X POST http://localhost:7860 -H "Content-Type: application/json" -d "$line"
done
