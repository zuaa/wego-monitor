package net.glamenvseptzen.quickstart

import org.jsoup.Jsoup
import org.jsoup.nodes.Document


def url="http://book.2345.com/index.php?c=catch&a=getContent&book_id=1319781&chapter_id=20398659&sign=2704ace352fcedc1e24e571d04ee2f7f"
Document doc = Jsoup.connect(url).get()
print doc.html();


