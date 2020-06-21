# coding=utf-8
import base64
import json
import sys

if __name__ == '__main__':
    for i in range(1, len(sys.argv)):
        print("参数", i, sys.argv[i])
    jsonObject = json.loads(base64.b64decode(sys.argv[1]))
    name = base64.b64decode(jsonObject['name'])
    print(name)
    number = base64.b64decode(jsonObject['number'])
    print(number)
