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
//class IKanylyzer {
//
//	public static void main(String[] args){
//		//Lucene Document������
//		String fieldName = "text";
//		//��������
//		String text = "IK Analyzer��һ����ϴʵ�ִʺ��ķ��ִʵ����ķִʿ�Դ���߰���ʹ����ȫ�µ���������ϸ�����з��㷨��";
//
//		//ʵ��IKAnalyzer�ִ���
//		Analyzer analyzer = new IKAnalyzer(true); 
//		Directory directory = null;
//		IndexWriter iwriter = null;
//		IndexReader ireader = null;
//		IndexSearcher isearcher = null;
//		try {
//			//�����ڴ��������
//			directory = new RAMDirectory();
//
//			//����IndexWriterConfig
//			IndexWriterConfig iwConfig = new IndexWriterConfig(Version.LUCENE_34 , analyzer);
//			iwConfig.setOpenMode(OpenMode.CREATE_OR_APPEND);
//			iwriter = new IndexWriter(directory , iwConfig);
//			//д������
//			Document doc = new Document();
//			doc.add(new Field("ID", "10000", Field.Store.YES, Field.Index.NOT_ANALYZED));
//			doc.add(new Field(fieldName, text, Field.Store.YES, Field.Index.ANALYZED));
//			iwriter.addDocument(doc);
//			iwriter.close();
//
//
//			//�������**********************************
//			//ʵ��������
//			ireader = IndexReader.open(directory);
//			isearcher = new IndexSearcher(ireader);
//
//			//			String keyword = "���ķִʹ��߰�";
//			String keyword = "IK Analyzer��һ����ϴʵ�ִ�";
//			//ʹ��QueryParser��ѯ����������Query����
//			QueryParser qp = new QueryParser(Version.LUCENE_34, fieldName, analyzer);
//			qp.setDefaultOperator(QueryParser.AND_OPERATOR);
//			Query query = qp.parse(keyword);
//			System.out.println("Query = " + query);
//
//			//�������ƶ���ߵ�5����¼
//			TopDocs topDocs = isearcher.search(query , 5);
//			System.out.println("���У�" + topDocs.totalHits);
//			//������
//			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
//			for (int i = 0; i < topDocs.totalHits; i++){
//				Document targetDoc = isearcher.doc(scoreDocs[i].doc);
//				System.out.println("���ݣ�" + targetDoc.toString());
//			}
//
//		} catch (CorruptIndexException e) {
//			e.printStackTrace();
//		} catch (LockObtainFailedException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ParseException e) {
//			e.printStackTrace();
//		} finally{
//			if(ireader != null){
//				try {
//					ireader.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//			if(directory != null){
//				try {
//					directory.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
//}
