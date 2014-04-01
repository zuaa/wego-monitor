//package com.heiji.analyzer
//
//import java.io.IOException;
//
//import org.apache.lucene.analysis.Analyzer;
//import org.apache.lucene.document.Document;
//import org.apache.lucene.document.Field;
//import org.apache.lucene.index.CorruptIndexException;
//import org.apache.lucene.index.IndexReader;
//import org.apache.lucene.index.IndexWriter;
//import org.apache.lucene.index.IndexWriterConfig;
//import org.apache.lucene.index.IndexWriterConfig.OpenMode;
//import org.apache.lucene.queryParser.ParseException;
//import org.apache.lucene.queryParser.QueryParser;
//import org.apache.lucene.search.IndexSearcher;
//import org.apache.lucene.search.Query;
//import org.apache.lucene.search.ScoreDoc;
//import org.apache.lucene.search.TopDocs;
//import org.apache.lucene.store.Directory;
//import org.apache.lucene.store.LockObtainFailedException;
//import org.apache.lucene.store.RAMDirectory;
//import org.apache.lucene.util.Version;
//import org.wltea.analyzer.lucene.IKAnalyzer;
//
//
//class CopyOfIKanylyzer {
//
//	public static void main(String[] args){
//		Analyzer analyzer = new IKAnalyzer(true);
//		String keyword = "IK Analyzer��һ����ϴʵ�ִ�zuaaa�ķ۴�";
//		QueryParser qp = new QueryParser(Version.LUCENE_34, "text", analyzer);
//		qp.setDefaultOperator(QueryParser.AND_OPERATOR);
//		Query query = qp.parse(keyword);
//		System.out.println("Query = " +query);
//	}
//}
