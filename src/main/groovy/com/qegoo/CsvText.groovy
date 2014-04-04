package com.qegoo

import com.xlson.groovycsv.CsvParser

def csv = '''Name-Lastname
Mark-'Anderson-Nielsen'
Pete-Hansen'''

def data = new CsvParser().parse(csv, separator: '-', quoteChar: "'")
for(line in data) {
	println "$line.Name $line.Lastname"
}

