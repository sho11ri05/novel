package novel;

import hiroi1.FirstShooting;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MyGameDisplay extends GameDisplay {

	GameDisplay title, start, center, center2, bad_end, happy_end;
	private Font mfont = new Font("Sanserif", Font.BOLD, 35);
	private Font mfont2 = new Font("Sanserif", Font.BOLD, 15);
	private int countPush = 0;
	private int countPush2 = 0;
	private int countPush3 =0;
	private int countPush4 =0;
	private int countPush5 =0;
	public int score = 0;  //スコア

	public MyGameDisplay(){
		this.title = new MyGameTitle();
		this.start = new MyGameStart();
		this.center = new MyGameCenter();
		this.center2 = new MyGameCenter2();
		this.bad_end = new MyGameBad_end();
		this.happy_end = new MyGameHappy_end();
		GameDisplay.current = this.title;
	}

//	//hiroi1
//	public void FirstShooting(){
//	    FirstShooting obj1 = new FirstShooting();
//	        obj1.getClass();
//	     }

//	//hiroi2
//	public void FirstShooting2(){
//	    FirstShooting obj = new FirstShooting();
//	        obj.getClass();
//	}


	@Override
	public void show(GraphicsInfo ginfo) {
	}

	@Override
	public void loadMedia() throws IOException {
		this.title.loadMedia();
		this.start.loadMedia();
		this.center.loadMedia();
		this.center2.loadMedia();
		this.bad_end.loadMedia();
		this.happy_end.loadMedia();
	}


	//内容
	//タイトル
	class MyGameTitle extends GameDisplay{
		private BufferedImage img_title; //タイトル画像

		@Override
		public void show(GraphicsInfo ginfo) {
			ginfo.g.drawImage(this.img_title, 0, 0, null);

			ginfo.g.setColor(Color.RED);
			ginfo.g.setFont(MyGameDisplay.this.mfont);
			String str = "Push State!";
			FontMetrics fm = ginfo.g.getFontMetrics();
			int strw = fm.stringWidth(str) / 2;
			ginfo.g.drawString(str, 200 - strw, 330);

			if(ginfo.keystate[KEY_STATE.SPACE] == true){
				GameDisplay.current = MyGameDisplay.this.start;
				/**
				 * 最後にコメントを取る
				GameDisplay.current = MyGameDisplay.this.start;
				**/
			}
		}

		@Override
		public void loadMedia() throws IOException {
			this.img_title = ImageIO.read(new File("gazou/title.jpg")); //タイトル画像
		}
	}




	//本編
	class  MyGameStart extends GameDisplay{
		private BufferedImage img_back1; //神殿
		private BufferedImage img_back2; //序章
		private BufferedImage img_loading; //ローディング仮想画像
		private BufferedImage img_waku; //台詞枠

		private BufferedImage img_apo_a; //アポロン（呆れ）
		private BufferedImage img_apo_k; //(笑顔)
		private BufferedImage img_apo_s; //（素）

		private BufferedImage img_al_a; //アルテミス（呆れ）
		private BufferedImage img_al_s; //（素）

		private BufferedImage img_zeu_a; //ゼウス（呆れ、焦り）
		private BufferedImage img_zeu_k; //（笑顔）
		private BufferedImage img_zeu_s; //（素）

		private BufferedImage img_hera_d; //ヘラ（怒り）


		@Override
		public void show(GraphicsInfo ginfo) {
			String str;
//			FirstShooting obj1 = new FirstShooting();

			//台詞代入
			String[] kotoba = {
					"",//0
					"",//1
					"",//2
					"ヘラ「ゼウス！！貴方という神はまた……！！」",//3
					"ゼウス「悪かった！悪かったって！！」　　　　",//4
					"オリュンポスに甲高い女性の声が木霊し、　　　",//5
					"同時に許しを請う男性の絶叫も木霊した　　　　",//6
					"ヘラ「私という妻がいながら、何故何度も浮気をするのです！？",//7
					"　　　もうこれで何十回目ですか！　　　　　　　　　　　　　",//8
					"　　　しかもそんな若振った姿をして……！！」　　　　　　　",//9
					"ゼウス「悪かったってヘラ！でもさ、あの子すっごい可愛くて…",//10
					"　　　　それに爺の姿より若い姿の方がやっぱカッコいいし…！",//11
					"　　　　というか、君だって毎年若返ってるじゃないか！」　　",//12
					"ヘラ「なっ…！！わたくしは毎年１回だけ沐浴をして、　　",//13
					"　　　貴方の為に若返って処女になっているのです！！　　",//14
					"貴方の様な不純な理由と一緒にしないでくださいまし！！」",//15
					"こんな事、オリュンポスでは日常茶飯事の出来事である　　",//16
					"ゼウスは全知全能の神であると共に、浮気症の神様であった",//17
					"神だけでなく、人間にさえ手を出し…故に子どもも沢山いた",//18
					"ヘラは毎回浮気を見つけ出し、相手の女性に呪いをかけるのだが",//19
					"一方ゼウスには謝罪の言葉はあれど、反省の色はなく　　　　　",//20
					"何度も何度も浮気を重ねているのである　　　　　　　　　　　",//21
					"ヘラ「なるほど貴方がそう言うのなら　　　　　　　　　　　　",//22
					"　　　…今度の小娘はヤギにでも変えてしまいましょうか！？」",//23
					"ゼウス「！！……それだけは勘弁してくれヘラ！！」　　　　　",//24
					"ヘラ「またそうやって庇う…！！　　　　　　　　　　　　　　",//25
					"　　　私がどんなに辛い思いをしているのかも知らないで！！」",//26
					"ゼウス「君には毎回悪いと思ってるし、言葉にしてるじゃないか…。」",//27
					"ヘラ「……今度ばかりは私も貴方に愛想がつきました……！」　　　　",//28
					"何度目かわからない浮気に、とうとうヘラが痺れを切らしたらしい",//29
					"ヘラ「しばらくハデスの所に身を置きます」　　　",//30
					"ゼウス「冥界に！？ちょ……待ってくれヘラ！！」",//31
					"ヘラ「いいえ待ちませんわ！！しばらく貴方の顔も見たくない！！」",//32
					"――――――――それがつい数時間前の話　　　　　",//33
					"ヘラは宣言通り冥界へ家出を、　　　　　　　　　　",//34
					"そしてゼウスはヘラを止めようにも為す術もなく、　",//35
					"天界のオリュンポスに残されてしまったのである……",//36
					"",//37
					"ゼウス「と、いう訳なんだ……」　　　　　　　　　",//38
					"？？？「そんなの、父上様が悪いに決まってますわ」",//39
					"？？？「オレも姉ちゃんにどうかーん♪」　　　　　",//40
					"ゼウス「そんな！い、いやそれはわかってる……　　　　　　　",//41
					"　　　　問題は、どうやってヘラを冥界から連れ戻すかなんだよ",//42
					"　　　　アルテミス、アポロンよ」　　　　　　　　　　　　　",//43
					"処女神で双子の姉のアルテミス、太陽神で双子の弟アポロン",//44
					"この二人もゼウスの子どもである　　　　　　　　　　　　",//45
					"……母親は、ヘラではないのだが　　　　　　　　　　　　",//46
					"アポロン「父上が冥界に行って連れ帰ればいいでしょー　　　　　　",//47
					"　　　　　ハデス様なんて、別に怖くないでしょー？父上ならー♪」",//48
					"アルテミス「それでは問題が解決しないでしょう？　　　　　　　　　",//49
					"　　　　　　ヘラ母さまは自分で冥界に行ったんだから　　　　　　　",//50
					"　　　　　　嫌々連れ帰ろうとしたら戦争が起きかねないでしょう？」",//51
					"アポロン「そっかー、　　　　　　　　　　　　　　　　　　　　　　　",//52
					"　　　　　冥界天界大戦争の引き金が夫婦喧嘩とかバカバカしいもんなー",//53
					"　　　　　そしたら、父上が星を割るぐらいの勢いでー　　　　　　　　",//54
					"　　　　　土下座するしかないんじゃないかなー？」　　　　　　　　　",//55
					"ゼウス「星を割るって……」　　　　　　　　　　　　　　　　　　",//56
					"アポロン「後は、父上がヘラ母さまのごきげんを取るしかないねー」",//57
					"ゼウス「ごきげん……例えばどうすればいいのだ？」　　",//58
					"アルテミス「父上様はいつも女性にされているでしょう？",//59
					"　　　　　　同じ様に致せば良いと思いますよ」　　　　",//60
					"ゼウス「……口説いてベットに連れてけば……？」　　　　　",//61
					"アポロン「父上ーオレが言うのも何だけど……見境なさ過ぎ」",//62
					"ゼウス「男に惚れちゃうお前に言われたくない台詞だな」",//63
					"アポロンも美男子であったがために、そこそこの浮気症であった",//64
					"……しかも、男女の見境もなかった。　　　　　　　　　　　　",//65
					"……まぁしかし、ゼウス程ではないようだが　　　　　　　　　",//66
					"ゼウス「オレはな、確かに沐浴後のヘラに愛を囁き育んでるさ　　　",//67
					"　　　　だがな、喧嘩した後に彼女を口説いた事は一度も無いんだよ",//68
					"　　　　……どうしたらいいものか……」　　　　　　　　　　　　",//69
					"アポロン「あ、ならプレゼントってのはどうです？父上！」　　",//70
					"ゼウス「プレゼント？」　　　　　　　　　　　　　　　　　　",//71
					"アルテミス「人間達が、親しい仲の者に何かを送る事のようです",//72
					"　　　　　　送るものは『物』なのが定番のようですよ？」　　　　",//73
					"ゼウス「なるほど、それはいい！……はて、何を贈ろうか…」",//74
					"アルテミス「昔…ヘラ母さまが『雪が見てみたい』と言ってましたね？」",//75
					"アポロン「あーそうそうー！言ってたねー！人間って、　　　　　　",//76
					"　　　　　雪で人形作るんだってー！スノーマン…って言ったかな？",//77
					"　　　　　あれを一度見てみたいってー！」　　　　　　　　　　　",//78
					"ゼウス「何でそんなもの…地上に行けばいくらでも見れるだろうに？」",//79
					"アルテミス「ヘラ母さまは頻繁に降臨される神様ではないですからね",//80
					"　　　　　　……父上様と違って」　　　　　　　　　　　　　　　",//81
					"アポロン「『ペルセポネとお茶ができるのは嬉しいけど、　　　",//82
					"　　　　　彼女がいる時が雪と言うものが降る時間なのですね』",//83
					"　　　　　…って言ってたなー」　　　　　　　　　　　　　　",//84
					"ゼウス「……なるほど、よし決めた！　　　　　　　　　　　",//85
					"　　　　スノーマンとやら、私が作ってやろうではないか！」",//86
					"こうしてヘラに捧げるスノーマンの材料を集めるべく　　",//87
					"ゼウスは地上に降り立ち、まずは雪を集める事にしました",//88
					"",//89
					""
			};

			str = kotoba[countPush];
			FontMetrics fm = ginfo.g.getFontMetrics();
			int strw = 0;//fm.stringWidth(str) / 2;
			ginfo.g.drawString(str, 200 - strw, 400);

			if(ginfo.keystate[KEY_STATE.SPACE] == true){
				countPush = countPush + 1;
				try{
					Thread.sleep(300); //0.5秒Sleepする
					}catch(InterruptedException e){}
			}



			//導入絵
			if(countPush == 1){
				ginfo.g.drawImage(this.img_back2, 0, 0, null);
				str = kotoba[countPush];
			}else{
			}



			//ローディング画面絵
			if(countPush == 2){
				ginfo.g.drawImage(this.img_loading, 0, 0, null);
				str = kotoba[countPush];
			}else{
			}



			//本編（ヘラ、ゼウス会話）
			//ヘラ、ゼウス、ナレーション
			if((countPush == 3) || (countPush == 4) || (countPush == 5)){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_d, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);
				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 350 - strw, 400);
			}else{
			}
			if(countPush == 6){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_d, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 350 - strw, 400);
				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 350 - strw, 420);
			}


			//ヘラ
			if(countPush == 7){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_d, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush == 8){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_d, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}
			if(countPush == 9){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_d, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush-2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 440);
			}

			//ゼウス
			if(countPush == 10){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_d, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush == 11){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_d, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}
			if(countPush == 12){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_d, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush-2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 440);
			}

			//ヘラ
			if(countPush == 13){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_d, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush == 14){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_d, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}
			if(countPush == 15){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_d, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush-2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 440);
			}

			//ナレーション１
			if(countPush == 16){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_d, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush == 17){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_d, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}
			if(countPush == 18){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_d, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush-2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 440);
			}

			//ナレーション２
			if(countPush == 19){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_d, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush == 20){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_d, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}
			if(countPush == 21){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_d, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush-2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 440);
			}

			//ヘラ
			if(countPush == 22){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_d, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush == 23){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_d, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//ゼウス
			if(countPush == 24){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_d, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ヘラ
			if(countPush == 25){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_d, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush == 26){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_d, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//ゼウス、ヘラ、ナレーション、ヘラ、ゼウス、ヘラ
			if((countPush == 27) || (countPush == 28) || (countPush == 29) || (countPush == 30) || (countPush == 31) || (countPush == 32)){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_d, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ナレーション
			if(countPush == 33){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush == 34){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}
			if(countPush == 35){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush-2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 440);
			}
			if(countPush == 36){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush-3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush-2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
				str = kotoba[countPush-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 440);
				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 460);
			}



			//ローディング画面絵
			if(countPush == 37){
				ginfo.g.drawImage(this.img_loading, 0, 0, null);
				str = kotoba[countPush];
			}else{
			}


			//ゼウス
			if(countPush == 38){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//アルテミス
			if(countPush == 39){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_al_s, 350, 95, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//アポロン
			if(countPush == 40){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_al_s, 350, 95, null);
				ginfo.g.drawImage(this.img_apo_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ゼウス
			if(countPush == 41){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_al_s, 350, 95, null);
				ginfo.g.drawImage(this.img_apo_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush == 42){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_al_s, 350, 95, null);
				ginfo.g.drawImage(this.img_apo_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}
			if(countPush == 43){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_al_s, 350, 95, null);
				ginfo.g.drawImage(this.img_apo_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush-2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 440);
			}

			//ナレーション
			if(countPush == 44){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_al_s, 350, 95, null);
				ginfo.g.drawImage(this.img_apo_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush == 45){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_al_s, 350, 95, null);
				ginfo.g.drawImage(this.img_apo_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}
			if(countPush == 46){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_al_s, 350, 95, null);
				ginfo.g.drawImage(this.img_apo_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush-2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 440);
			}


			//アポロン
			if(countPush == 47){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_al_s, 350, 95, null);
				ginfo.g.drawImage(this.img_apo_k, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush == 48){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_al_s, 350, 95, null);
				ginfo.g.drawImage(this.img_apo_k, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//アルテミス
			if(countPush == 49){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_apo_s, 500, 80, null);
				ginfo.g.drawImage(this.img_al_a, 350, 95, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush == 50){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_apo_s, 500, 80, null);
				ginfo.g.drawImage(this.img_al_a, 350, 95, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}
			if(countPush == 51){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_apo_s, 500, 80, null);
				ginfo.g.drawImage(this.img_al_a, 350, 95, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush-2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 440);
			}


			//アポロン
			if(countPush == 52){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_al_s, 350, 95, null);
				ginfo.g.drawImage(this.img_apo_a, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush == 53){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_al_s, 350, 95, null);
				ginfo.g.drawImage(this.img_apo_a, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}
			if(countPush == 54){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_al_s, 350, 95, null);
				ginfo.g.drawImage(this.img_apo_k, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush-2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 440);
			}
			if(countPush == 55){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_al_s, 350, 95, null);
				ginfo.g.drawImage(this.img_apo_k, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush-3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush-2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
				str = kotoba[countPush-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 440);
				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 460);
			}

			//ゼウス、アポロン
			if((countPush == 56) || (countPush == 57)){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_al_s, 350, 95, null);
				ginfo.g.drawImage(this.img_apo_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ゼウス
			if((countPush == 58)){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_al_s, 350, 95, null);
				ginfo.g.drawImage(this.img_apo_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//アルテミス
			if(countPush == 59){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_apo_s, 500, 80, null);
				ginfo.g.drawImage(this.img_al_s, 350, 95, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush == 60){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_apo_s, 500, 80, null);
				ginfo.g.drawImage(this.img_al_s, 350, 95, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//ゼウス
			if((countPush == 61)){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_al_s, 350, 95, null);
				ginfo.g.drawImage(this.img_apo_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//アポロン
			if((countPush == 62)){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_al_a, 350, 95, null);
				ginfo.g.drawImage(this.img_apo_a, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}


			//ゼウス
			if((countPush == 63)){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_al_a, 350, 95, null);
				ginfo.g.drawImage(this.img_apo_a, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}


			//ナレーション
			if(countPush == 64){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_al_a, 350, 95, null);
				ginfo.g.drawImage(this.img_apo_a, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush == 65){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_al_a, 350, 95, null);
				ginfo.g.drawImage(this.img_apo_a, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}
			if(countPush == 66){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_al_a, 350, 95, null);
				ginfo.g.drawImage(this.img_apo_a, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush-2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 440);
			}

			//ゼウス
			if(countPush == 67){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_al_s, 350, 95, null);
				ginfo.g.drawImage(this.img_apo_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush == 68){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_al_s, 350, 95, null);
				ginfo.g.drawImage(this.img_apo_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}
			if(countPush == 69){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_al_s, 350, 95, null);
				ginfo.g.drawImage(this.img_apo_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush-2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 440);
			}

			//アポロン、ゼウス
			if((countPush == 70) || (countPush == 71)){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_al_s, 350, 95, null);
				ginfo.g.drawImage(this.img_apo_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//アルテミス
			if(countPush == 72){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_apo_s, 500, 80, null);
				ginfo.g.drawImage(this.img_al_s, 350, 95, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush == 73){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_apo_s, 500, 80, null);
				ginfo.g.drawImage(this.img_al_s, 350, 95, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//ゼウス、アルテミス
			if((countPush == 74) || (countPush == 75)){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_k, 20, 80, null);
				ginfo.g.drawImage(this.img_apo_s, 500, 80, null);
				ginfo.g.drawImage(this.img_al_s, 350, 95, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//アポロン
			if(countPush == 76){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_k, 20, 80, null);
				ginfo.g.drawImage(this.img_al_s, 350, 95, null);
				ginfo.g.drawImage(this.img_apo_k, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush == 77){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_k, 20, 80, null);
				ginfo.g.drawImage(this.img_al_s, 350, 95, null);
				ginfo.g.drawImage(this.img_apo_k, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}
			if(countPush == 78){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_k, 20, 80, null);
				ginfo.g.drawImage(this.img_al_s, 350, 95, null);
				ginfo.g.drawImage(this.img_apo_k, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush-2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 440);
			}

			//ゼウス
			if(countPush == 79){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_al_s, 350, 95, null);
				ginfo.g.drawImage(this.img_apo_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//アルテミス
			if(countPush == 80){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_apo_a, 500, 80, null);
				ginfo.g.drawImage(this.img_al_a, 350, 95, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush == 81){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_apo_a, 500, 80, null);
				ginfo.g.drawImage(this.img_al_a, 350, 95, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//アポロン
			if(countPush == 82){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_al_s, 350, 95, null);
				ginfo.g.drawImage(this.img_apo_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush == 83){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_al_s, 350, 95, null);
				ginfo.g.drawImage(this.img_apo_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}
			if(countPush == 84){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_al_s, 350, 95, null);
				ginfo.g.drawImage(this.img_apo_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush-2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 440);
			}

			//ゼウス
			if(countPush == 85){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_al_s, 350, 95, null);
				ginfo.g.drawImage(this.img_apo_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush == 86){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_k, 20, 80, null);
				ginfo.g.drawImage(this.img_al_s, 350, 95, null);
				ginfo.g.drawImage(this.img_apo_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//ナレーション
			if(countPush == 87){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_k, 20, 80, null);
				ginfo.g.drawImage(this.img_al_s, 350, 95, null);
				ginfo.g.drawImage(this.img_apo_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush == 88){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_k, 20, 80, null);
				ginfo.g.drawImage(this.img_al_s, 350, 95, null);
				ginfo.g.drawImage(this.img_apo_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}


			//ローディング画面絵
			if(countPush == 89){
				ginfo.g.drawImage(this.img_loading, 0, 0, null);
				str = kotoba[countPush];
			}else{
			}

			if(countPush == 90){
				//パッケージhiroi1のGameDisplayに飛ぶ
				FirstShooting obj1 = new FirstShooting();
//				MyGameDisplay obj1 = new MyGameDisplay();
		        obj1.getClass();
			}else{
			}

		}


		@Override
		public void loadMedia() throws IOException {
			this.img_back1 = ImageIO.read(new File("gazou/rouka00.jpg"));
			this.img_back2 = ImageIO.read(new File("gazou/sky2.png"));
			this.img_loading = ImageIO.read(new File("gazou/loading.jpg"));
			this.img_waku = ImageIO.read(new File("gazou/ant1.png"));

			this.img_apo_a = ImageIO.read(new File("apolon/aporon_a.png"));//アポロン画像
			this.img_apo_k = ImageIO.read(new File("apolon/aporon_k.png"));
			this.img_apo_s = ImageIO.read(new File("apolon/aporon_s.png"));

			this.img_al_a = ImageIO.read(new File("altemis/arutemisu_a.png"));//アルテミス画像
			this.img_al_s = ImageIO.read(new File("altemis/arutemisu_s.png"));

			this.img_zeu_a = ImageIO.read(new File("zeus/zeusu_a.png"));//ゼウス画像
			this.img_zeu_k = ImageIO.read(new File("zeus/zeusu_k.png"));
			this.img_zeu_s = ImageIO.read(new File("zeus/zeusu_s.png"));


			this.img_hera_d = ImageIO.read(new File("hera/hera_d.png"));//ヘラ画像
		}
	}







	//中間地点
	class MyGameCenter extends GameDisplay{
		private BufferedImage img_back1; //雪背景
		private BufferedImage img_back2; //冥界背景
		private BufferedImage img_loading; //ローディング仮想画像２
		private BufferedImage img_waku; //台詞枠

		private BufferedImage img_zeu_d; //ゼウス（怒り）
		private BufferedImage img_zeu_k; //（笑顔）
		private BufferedImage img_zeu_s; //（素）

		private BufferedImage img_hade_a; //ハデス（呆れ）
		private BufferedImage img_hade_s; //（素）

		private BufferedImage img_heru_s; //ヘルメス（素）
		private BufferedImage img_heru_k; //


		@Override
		public void show(GraphicsInfo ginfo) {
			String str;

			//台詞代入
			String kotoba[]={"",//0
					"ゼウス「よし、このくらいでいいだろう！」",//1
					"空から降ってきた綺麗な雪で、雪だるまを作っていたゼウスに",//2
					"天から一人の神が舞い降りてきた　　　　　　　　　　　　　",//3
					"？？？「父上様、大変でございます」　　　　　　",//4
					"ゼウス「ん？どうしたヘルメスよ？」　　　　　　",//5
					"旅人や、商売の神等の呼び名があるヘルメス、彼もゼウスの子である",//6
					"母はヘラではないが、彼はヘラに我が子の様に育てられている",//7
					"頭が良く、ゼウスの伝令役として各地を飛んでいる神でもある　　　",//8
					"ヘルメス「先刻、この様な手紙を叔父上様より預かってきました」　　",//9
					"ゼウス「ん？ハデスから手紙？…珍しい」　　　　　　",//10
					"そう言って、ヘルメスから手紙を受け取ったゼウスは、",//11
					"手紙を読んでからヘルメスを見る　　　　　　　　　　",//12
					"ゼウス「…どういうことだ？ハデスがヘラを返さないだと…？」　　　",//13
					"ヘルメス「左様で御座います、ペルセポネ様もその意思のご様子で…」",//14
					"ゼウス「ふざけるな！そんなの許せる訳ないだろう！！」",//15
					"そう言うと、風の様にゼウスは消え去ってしまった",//16
					"残ったヘルメスはというと…　　　　　　　　　　",//17
					"ヘルメス「…やれやれ、困った父上様と母上様だな…　　　　",//18
					"　　　　　息子と娘がこうも手を焼かないといけないとは…」",//19
					"消え去ったゼウスの後を見て、ひっそり笑うヘルメスを",//20
					"ゼウスは知るよしもなかった……　　　　　　　　　　",//21
					"",//22
					"ゼウス「ハデス！ハーデース！！」",//23
					"？？？「……煩いぞ、末弟よ」",//24
					"冥界、死者の国にやってきたゼウスは、崖の対岸に彼を呼び出した",//25
					"彼はハデス。絶対なる冥界の王であり、　　　　　　　　　　　　",//26
					"ヘラの弟、そしてゼウスの兄でもある　　　　　　　　　　",//27
					"ゼウス「煩いじゃねぇよ！！何だこの手紙は！！」",//28
					"ハデス「……手紙？……はて、何の事だ？」",//29
					"ゼウス「とぼけんな！！いいから城へ向かう為の橋を出せ！！」",//30
					"ハデス「……生憎それはできんな。何せ姉さんは大層ご立腹だ。　　　",//31
					"　　　　私の冥界で神々夫婦戦争なんぞ起こされたらひとたまりもない",//32
					"　　　　よって、姉さんの怒りが静まるまで、お前は帰って寝てろ」　",//33
					"ゼウス「さっきっから好き勝手言いやがって…！　　　　　　　",//34
					"　　　　わかったよ！オレがそっちに飛んで向かってやらぁ！」",//35
					"ハデス「この冥界の雷を避けて渡るというのか…？",//36
					"　　　　……本当に、お前は何時でも力任せだな」",//37
					"ゼウス「うるせぇ！ヘラの為なら何だってやってやらぁ！！」",//38
					"かくして、ゼウスはハデスの宮殿へ向かうため　",//39
					"雷が上下から降り注ぐ谷を渡る事としたのです。",//40
					"",//41
					""
			};

			str = kotoba[countPush2];
			FontMetrics fm = ginfo.g.getFontMetrics();
			int strw = 0;//fm.stringWidth(str) / 2;
			ginfo.g.drawString(str, 200 - strw, 400);

			if(ginfo.keystate[KEY_STATE.SPACE] == true){
				countPush2 = countPush2 + 1;
				try{
					Thread.sleep(300); //0.5秒Sleepする
					}catch(InterruptedException e){}
			}


			//ローディング画面絵
			if(countPush2 == 0){
				ginfo.g.drawImage(this.img_loading, 0, 0, null);
				str = kotoba[countPush2];
			}else{
			}

			//ゼウス
			if(countPush2 == 1){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_k, 20, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}else{
			}

			//ナレーション
			if(countPush2 == 2){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_k, 20, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush2 == 3){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_k, 20, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush2-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//ヘルメス、ゼウス
			if((countPush2 == 4) || (countPush2 == 5)){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_heru_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ナレーション
			if(countPush2 == 6){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_heru_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush2 == 7){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_heru_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush2-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}
			if(countPush2 == 8){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_heru_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush2-2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush2-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
				str = kotoba[countPush2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 440);
			}

			//ヘルメス、ゼウス
			if((countPush2 == 9) || (countPush2 == 10)){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_heru_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ナレーション
			if(countPush2 == 11){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_heru_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush2 == 12){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_heru_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush2-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//ゼウス、ヘルメス、ゼウス
			if((countPush2 == 13) || (countPush2 == 14) || (countPush2 == 15)){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_d, 20, 80, null);
				ginfo.g.drawImage(this.img_heru_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ナレーション
			if(countPush2 == 16){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_heru_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush2 == 17){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_heru_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush2-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//ヘルメス
			if(countPush2 == 18){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_heru_k, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush2 == 19){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_heru_k, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush2-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//ナレーション
			if(countPush2 == 20){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_heru_k, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush2 == 21){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_heru_k, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush2-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//ローディング画面絵
			if(countPush2 == 22){
				ginfo.g.drawImage(this.img_loading, 0, 0, null);
				str = kotoba[countPush2];
			}

			//ここから冥界
			//ゼウス
			if(countPush2 == 23){
				ginfo.g.drawImage(this.img_back2, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_d, 20, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ハデス
			if(countPush2 == 24){
				ginfo.g.drawImage(this.img_back2, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_d, 20, 80, null);
				ginfo.g.drawImage(this.img_hade_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ナレーション
			if(countPush2 == 25){
				ginfo.g.drawImage(this.img_back2, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_d, 20, 80, null);
				ginfo.g.drawImage(this.img_hade_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush2 == 26){
				ginfo.g.drawImage(this.img_back2, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_d, 20, 80, null);
				ginfo.g.drawImage(this.img_hade_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush2-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}
			if(countPush2 == 27){
				ginfo.g.drawImage(this.img_back2, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_d, 20, 80, null);
				ginfo.g.drawImage(this.img_hade_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush2-2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush2-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
				str = kotoba[countPush2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 440);
			}

			//ゼウス、ハデス、ゼウス
			if((countPush2 == 28) || (countPush2 == 29) || (countPush2 == 30)){
				ginfo.g.drawImage(this.img_back2, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_d, 20, 80, null);
				ginfo.g.drawImage(this.img_hade_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ハデス
			if(countPush2 == 31){
				ginfo.g.drawImage(this.img_back2, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_d, 20, 80, null);
				ginfo.g.drawImage(this.img_hade_a, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush2 == 32){
				ginfo.g.drawImage(this.img_back2, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_d, 20, 80, null);
				ginfo.g.drawImage(this.img_hade_a, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush2-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}
			if(countPush2 == 33){
				ginfo.g.drawImage(this.img_back2, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_d, 20, 80, null);
				ginfo.g.drawImage(this.img_hade_a, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush2-2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush2-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
				str = kotoba[countPush2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 440);
			}

			//ゼウス
			if(countPush2 == 34){
				ginfo.g.drawImage(this.img_back2, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_d, 20, 80, null);
				ginfo.g.drawImage(this.img_hade_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush2 == 35){
				ginfo.g.drawImage(this.img_back2, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_d, 20, 80, null);
				ginfo.g.drawImage(this.img_hade_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush2-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//ハデス
			if(countPush2 == 36){
				ginfo.g.drawImage(this.img_back2, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_d, 20, 80, null);
				ginfo.g.drawImage(this.img_hade_a, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush2 == 37){
				ginfo.g.drawImage(this.img_back2, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_d, 20, 80, null);
				ginfo.g.drawImage(this.img_hade_a, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush2-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//ゼウス
			if(countPush2 == 38){
				ginfo.g.drawImage(this.img_back2, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_d, 20, 80, null);
				ginfo.g.drawImage(this.img_hade_a, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ナレーション
			if(countPush2 == 39){
				ginfo.g.drawImage(this.img_back2, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_d, 20, 80, null);
				ginfo.g.drawImage(this.img_hade_a, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush2 == 40){
				ginfo.g.drawImage(this.img_back2, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_d, 20, 80, null);
				ginfo.g.drawImage(this.img_hade_a, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush2-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//ローディング画面絵
			if(countPush2 == 41){
				ginfo.g.drawImage(this.img_loading, 0, 0, null);
				str = kotoba[countPush2];
			}else{
			}

			if(countPush2 == 42){
				GameDisplay.current = MyGameDisplay.this.center2;
			}else{
			}

		}

		@Override
		public void loadMedia() throws IOException {
			this.img_back1 = ImageIO.read(new File("gazou/hiroi2-2.jpg"));//雪背景
			this.img_back2 = ImageIO.read(new File("gazou/meikai.png"));//冥界背景
			this.img_loading = ImageIO.read(new File("gazou/loading2.jpg"));//ローディング画像２
			this.img_waku = ImageIO.read(new File("gazou/ant1.png"));

			this.img_zeu_d = ImageIO.read(new File("zeus/zeusu_d.png"));//ゼウス画像
			this.img_zeu_k = ImageIO.read(new File("zeus/zeusu_k.png"));
			this.img_zeu_s = ImageIO.read(new File("zeus/zeusu_s.png"));

			this.img_hade_a = ImageIO.read(new File("hadesu/hadesu_a.png"));//ハデス画像
			this.img_hade_s = ImageIO.read(new File("hadesu/hadesu_s.png"));

			this.img_heru_k = ImageIO.read(new File("herumesu/herumesu_k.png"));//ヘルメス画像
			this.img_heru_s = ImageIO.read(new File("herumesu/herumesu_s.png"));




		}

	}




	//中間２（冥界ハデス、ペルセポネ編）
	class MyGameCenter2 extends GameDisplay{
		private BufferedImage img_back1; //冥界宮殿背景
		private BufferedImage img_back2; //冥界背景
		private BufferedImage img_loading; //ローディング仮想画像
		private BufferedImage img_waku; //台詞枠

		private BufferedImage img_zeu_a; //ゼウス（呆れ、焦り）
		private BufferedImage img_zeu_d; //（怒り）
		private BufferedImage img_zeu_k; //（笑顔）
		private BufferedImage img_zeu_s; //（素）

		private BufferedImage img_hade_a; //ハデス（呆れ）
		private BufferedImage img_hade_s; //（素）

		private BufferedImage img_peru_n; //ペルセポネ（笑顔）
		private BufferedImage img_peru_s; //（素）

		@Override
		public void show(GraphicsInfo ginfo) {
			String str;

			//台詞代入
			String kotoba[]={"",//0
					"ハデス「やれやれ…本当にあの雷をかいくぐってくるとは」",//1
					"ゼウス「さぁ、この手紙はどういう事なんだ！？」　　　　",//2
					"ハデス「だから何の事だと……」　　　　　　　　　　　　",//3
					"ハデスはゼウスから手紙を受け取り、中を見る",//4
					"……と、同時に呆れ返った　　　　　　　　　",//5
					"ハデス「……お前は何千年私の字を見ているんだ？末弟よ」",//6
					"ゼウス「……は？？」　　　　　　　　　　　　　　　　　",//7
					"ハデス「これは、私の字ではない。よく見ろ」　　　　　　",//8
					"ゼウス「…………本当、だな………」　　　　　　　　　　",//9
					"そう、書いてあった文字はハデスの字ではなかったのだ　　",//10
					"ゼウスのとんだ早とちりに、ハデスは大きくため息をついた",//11
					"ハデス「この字……私の妻、ペルセポネの字だ　　　　　",//12
					"　　　　恐らく、お前を呼び出す為に仕組んだんだろう」",//13
					"そう言うと、ハデスは城の門を開けた",//14
					"ゼウス「……夫婦喧嘩するから、帰らせるんじゃなかったのかよ？」",//15
					"ハデス「本来ならばな……しかし、ペルセポネが呼び寄せたのなら、",//16
					"　　　　無下に帰す訳にはいかないだろう」　　　　　　　　　　　",//17
					"ゼウス「ホント、お前ペルセポネには甘いよなぁ……」",//18
					"ハデスは無理やり地上から連れて来て嫁にしたペルセポネの為に、",//19
					"妻が住みやすいように、冥界を変えてしまう程の愛妻家であった　",//20
					"ハデス「……お前も、少しは姉さんの事を大切にしてやれ……」",//21
					"ゼウス「……オレなりに、してるつもりだがな」",//22
					"ハデス「『してるつもり』ではなく、しろ」",//23
					"ゼウス「……おう、わかった……」　　　　",//24
					"珍しく強気なハデスに気圧されつつ、ゼウスは宮殿の中へ向かった",//25
					"ハデス「……一々、お前の夫婦喧嘩に付き合ってたら、　",//26
					"　　　　ペルセポネとの時間が減ってしまうだろうが……」",//27
					"ゼウスが去ったのを確認してから、ハデスはそう本音を口にした。",//28
					"",//29
					"ゼウス「ペルセポネ！」　　　　　　　",//30
					"ペルセポネ「父上！」　　　　　　　　",//31
					"宮殿の自室に彼女はいた。ペルセポネ、彼女も実はゼウスの娘である",//32
					"事情は割愛するが、訳あって１年の３分の１のみを冥界で過ごし",//33
					"残りの季節は母親と共に地上で過ごす、春の女神である　　　",//34
					"ゼウス「ペルセポネ、これは君が書いた物なのか？」",//35
					"ペルセポネ「あら…父上、もうお気づきになられたのですか？」",//36
					"ゼウス「……ついさっき、だがな」　　　　　　　　　　　　　",//37
					"ペルセポネ「申し訳ありません、ヘラ様はココにはもう居られません」",//38
					"ゼウス「！？　どういう事だペルセポネ！？」　　　　　　　",//39
					"ペルセポネ「……ヘラ様は、本当に怒ってらっしゃるのだと、",//40
					"　　　　　　父上は思っておられるのですか？」",//41
					"ゼウス「ん？…そ、そうなんじゃないのか…？」",//42
					"ペルセポネ「……そう思うのであれば、父上もまだまだ　　",//43
					"　　　　　　乙女心は熟知できてはおられないようですね」",//44
					"そう言い、珍しくペルセポネは笑った",//45
					"ゼウス「んん？？ペルセポネよ、この父にもわかるように",//46
					"　　　　教えてはくれまいか？」　　　　　　　　　　　",//47
					"ペルセポネ「そうですね…ヘラ様は父上を怒っているのではなく　",//48
					"　　　　　　父上がなさったことを悲しんでおられるのですよ？」",//49
					"ゼウス「悲しんでる？？ヘラが何故悲しむのだ？？」　　",//50
					"ペルセポネ「父上を、本当にお慕いしているからですよ」",//51
					"ゼウスには、わからなかった。何故悲しいのかと　　　　　",//52
					"何故ならそんな経験はしたことがない　　　　　　　　　　",//53
					"ヘラは一途にゼウスを慕い、オリンポス神の中でも数少ない",//54
					"貞操を守りぬいている、結婚の女神なのだから……　　　　",//55
					"ペルセポネ「父上、ヘラ様を本当にお慕いしてますか？」",//56
					"ゼウス「当たり前だ！オレの正妻はヘラだけだ！」　　　",//57
					"ペルセポネ「……では、父上、その言葉わたくしではなく　　",//58
					"　　　　　　ヘラ様に直接お伝えくださいまし　　　　　　　",//59
					"　　　　　　ヘラ様はカナートスの泉にて沐浴されています」",//60
					"ゼウス「カナートス！ありがとうペルセポネ！」",//61
					"そう告げると、またもゼウスは風の様に去っていった",//62
					"ペルセポネ「ハデス様、いつまでそこに居るのですか？」",//63
					"ペルセポネは部屋の外にいたハデスを呼んだ",//64
					"ハデス「……これは、君の案かい？」",//65
					"ペルセポネ「まさか！アポロンとアルテミスが　　　　　　　　",//66
					"　　　　　　『お力を貸してください！』って言うものですから",//67
					"　　　　　　ちょっと手を貸しただけですわ」　　　　　　　　",//68
					"ハデス「……そうか、お前は本当に優しいな」",//69
					"ペルセポネ「ありがとうございます」　　　　",//70
					"冥界に来た波乱は、どうやら終止符を打たれたらしい",//71
					"さて、ゼウスの作戦は上手く行くのだろうか……？　",//72
					"",//73
					"",//74
			};

			str = kotoba[countPush3];
			FontMetrics fm = ginfo.g.getFontMetrics();
			int strw = 0;//fm.stringWidth(str) / 2;
			ginfo.g.drawString(str, 200 - strw, 400);

			if(ginfo.keystate[KEY_STATE.SPACE] == true){
				countPush3 = countPush3 + 1;
				try{
					Thread.sleep(300); //0.5秒Sleepする
					}catch(InterruptedException e){}
			}


			//ローディング画面絵
			if(countPush3 == 0){
				ginfo.g.drawImage(this.img_loading, 0, 0, null);
				str = kotoba[countPush3];
			}else{
			}

			//ハデス、ゼウス、ハデス
			if((countPush3 == 1) || (countPush3 == 2) || (countPush3 == 3)){
				ginfo.g.drawImage(this.img_back2, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_d, 20, 80, null);
				ginfo.g.drawImage(this.img_hade_a, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ナレーション
			if(countPush3 == 4){
				ginfo.g.drawImage(this.img_back2, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_d, 20, 80, null);
				ginfo.g.drawImage(this.img_hade_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush3 == 5){
				ginfo.g.drawImage(this.img_back2, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_d, 20, 80, null);
				ginfo.g.drawImage(this.img_hade_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//ハデス、ゼウス、ハデス
			if((countPush3 == 6) || (countPush3 == 7) || (countPush3 == 8)){
				ginfo.g.drawImage(this.img_back2, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_d, 20, 80, null);
				ginfo.g.drawImage(this.img_hade_a, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ゼウス
			if(countPush3 == 9){
				ginfo.g.drawImage(this.img_back2, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hade_a, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ナレーション
			if(countPush3 == 10){
				ginfo.g.drawImage(this.img_back2, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hade_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush3 == 11){
				ginfo.g.drawImage(this.img_back2, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hade_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//ハデス
			if(countPush3 == 12){
				ginfo.g.drawImage(this.img_back2, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_hade_a, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush3 == 13){
				ginfo.g.drawImage(this.img_back2, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_hade_a, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//ナレーション、ゼウス
			if((countPush3 == 14) || (countPush3 == 15)){
				ginfo.g.drawImage(this.img_back2, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_hade_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ハデス
			if(countPush3 == 16){
				ginfo.g.drawImage(this.img_back2, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_hade_a, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush3 == 17){
				ginfo.g.drawImage(this.img_back2, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_hade_a, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//ゼウス
			if(countPush3 == 18){
				ginfo.g.drawImage(this.img_back2, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hade_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ナレーション
			if(countPush3 == 19){
				ginfo.g.drawImage(this.img_back2, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_hade_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush3 == 20){
				ginfo.g.drawImage(this.img_back2, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_hade_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//ハデス、ゼウス、ハデス
			if((countPush3 == 21) ||(countPush3 == 22) || (countPush3 == 23)){
				ginfo.g.drawImage(this.img_back2, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_hade_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ゼウス
			if((countPush3 == 24) ||(countPush3 == 25)){
				ginfo.g.drawImage(this.img_back2, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hade_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ハデス
			if(countPush3 == 26){
				ginfo.g.drawImage(this.img_back2, 0, 0, null);
				ginfo.g.drawImage(this.img_hade_a, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush3 == 27){
				ginfo.g.drawImage(this.img_back2, 0, 0, null);
				ginfo.g.drawImage(this.img_hade_a, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//ナレーション
			if(countPush3 == 28){
				ginfo.g.drawImage(this.img_back2, 0, 0, null);
				ginfo.g.drawImage(this.img_hade_a, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ローディング画面絵
			if(countPush3 == 29){
				ginfo.g.drawImage(this.img_loading, 0, 0, null);
				str = kotoba[countPush3];
			}

			//ゼウス、ペルセポネ
			if((countPush3 == 30) || (countPush3 == 31)){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_k, 20, 80, null);
				ginfo.g.drawImage(this.img_peru_s, 450, 120, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ナレーション
			if(countPush3 == 32){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_k, 20, 80, null);
				ginfo.g.drawImage(this.img_peru_s, 450, 120, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush3 == 33){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_k, 20, 80, null);
				ginfo.g.drawImage(this.img_peru_s, 450, 120, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}
			if(countPush3 == 34){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_k, 20, 80, null);
				ginfo.g.drawImage(this.img_peru_s, 450, 120, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3-2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush3-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 440);
			}

			//ゼウス、ペルセポネ、ゼウス、ペルセポネ、ゼウス
			if((countPush3 == 35) || (countPush3 == 36) || (countPush3 == 37) || (countPush3 == 38) || (countPush3 == 39)){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_peru_s, 450, 120, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ペルセポネ
			if(countPush3 == 40){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_peru_s, 450, 120, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush3 == 41){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_peru_s, 450, 120, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//ゼウス
			if(countPush3 == 42){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_peru_s, 450, 120, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ペルセポネ
			if(countPush3 == 43){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_peru_n, 450, 120, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush3 == 44){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_peru_n, 450, 120, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//ナレーション
			if(countPush3 == 45){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_peru_n, 450, 120, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ゼウス
			if(countPush3 == 46){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_peru_n, 450, 120, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush3 == 47){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_peru_n, 450, 120, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//ペルセポネ
			if(countPush3 == 48){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_peru_n, 450, 120, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush3 == 49){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_peru_n, 450, 120, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//ゼウス、ペルセポネ
			if((countPush3 == 50) || (countPush3 == 51)){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_peru_n, 450, 120, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ナレーション
			if(countPush3 == 52){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_peru_n, 450, 120, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush3 == 53){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_peru_n, 450, 120, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}
			if(countPush3 == 54){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_peru_n, 450, 120, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3-2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush3-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 440);
			}
			if(countPush3 == 55){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_peru_n, 450, 120, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3-3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush3-2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
				str = kotoba[countPush3-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 440);
				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 460);
			}

			//ペルセポネ
			if(countPush3 == 56){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_peru_n, 450, 120, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ゼウス
			if(countPush3 == 57){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_k, 20, 80, null);
				ginfo.g.drawImage(this.img_peru_n, 450, 120, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ペルセポネ
			if(countPush3 == 58){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_peru_n, 450, 120, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush3 == 59){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_peru_n, 450, 120, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}
			if(countPush3 == 60){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_peru_n, 450, 120, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3-2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush3-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 440);
			}

			//ゼウス
			if(countPush3 == 61){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_k, 20, 80, null);
				ginfo.g.drawImage(this.img_peru_n, 450, 120, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ナレーション
			if(countPush3 == 62){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_peru_n, 450, 120, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ペルセポネ、ナレーション
			if((countPush3 == 63) || (countPush3 == 64)){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_peru_s, 20, 120, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ハデス
			if(countPush3 == 65){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_peru_s, 20, 120, null);
				ginfo.g.drawImage(this.img_hade_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ペルセポネ
			if(countPush3 == 66){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_peru_s, 20, 120, null);
				ginfo.g.drawImage(this.img_hade_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush3 == 67){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_peru_s, 20, 120, null);
				ginfo.g.drawImage(this.img_hade_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}
			if(countPush3 == 68){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_peru_s, 20, 120, null);
				ginfo.g.drawImage(this.img_hade_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3-2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush3-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 440);
			}

			//ハデス
			if(countPush3 == 69){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_peru_s, 20, 120, null);
				ginfo.g.drawImage(this.img_hade_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ペルセポネ
			if(countPush3 == 70){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_peru_n, 20, 120, null);
				ginfo.g.drawImage(this.img_hade_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ナレーション
			if(countPush3 == 71){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_peru_n, 20, 120, null);
				ginfo.g.drawImage(this.img_hade_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush3 == 72){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_peru_n, 20, 120, null);
				ginfo.g.drawImage(this.img_hade_s, 500, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush3-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush3];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//ローディング画面絵
			if(countPush3 == 73){
				ginfo.g.drawImage(this.img_loading, 0, 0, null);
				str = kotoba[countPush3];
			}else{
			}

			if((countPush3 == 74) && (score >= 10)){
				GameDisplay.current = MyGameDisplay.this.happy_end;
			}else if((countPush3 == 74) && (score <= 10)){
				GameDisplay.current = MyGameDisplay.this.bad_end;
			}



			}

		@Override
		public void loadMedia() throws IOException {
			this.img_back1 = ImageIO.read(new File("gazou/maid_room000.jpg"));//冥界宮殿背景
			this.img_back2 = ImageIO.read(new File("gazou/meikai.png"));//冥界背景
			this.img_loading = ImageIO.read(new File("gazou/loading.jpg"));//ローディング画像
			this.img_waku = ImageIO.read(new File("gazou/ant1.png"));//台詞わく

			this.img_zeu_a = ImageIO.read(new File("zeus/zeusu_a.png"));//ゼウス画像
			this.img_zeu_d = ImageIO.read(new File("zeus/zeusu_d.png"));
			this.img_zeu_k = ImageIO.read(new File("zeus/zeusu_k.png"));
			this.img_zeu_s = ImageIO.read(new File("zeus/zeusu_s.png"));

			this.img_hade_a = ImageIO.read(new File("hadesu/hadesu_a.png"));//ハデス画像
			this.img_hade_s = ImageIO.read(new File("hadesu/hadesu_s.png"));

			this.img_peru_n = ImageIO.read(new File("peruse/perusepone_n.png"));//ペルセポネ画像
			this.img_peru_s = ImageIO.read(new File("peruse/perusepone_s.png"));
		}

	}








	//バッドエンド
	class MyGameBad_end extends GameDisplay{
		private BufferedImage img_back1; //泉
		private BufferedImage img_loading; //ローディング仮想画像
		private BufferedImage img_waku; //台詞枠

		private BufferedImage img_zeu_a; //ゼウス（呆れ、焦り）
		private BufferedImage img_zeu_k; //（笑顔）
		private BufferedImage img_zeu_s; //（素）

		private BufferedImage img_hera_d; //ヘラ（怒り）
		private BufferedImage img_hera_s; //（素）
		private BufferedImage img_hera_t; //（照れ）

		@Override
		public void show(GraphicsInfo ginfo) {
			String str;

			//台詞代入
			String kotoba[]={"",//0
					"ゼウス「ヘラ！！」　　　　　　　　　　　　　　　　",//1
					"ヘラ「！！？？　あなた！！？？」　　　　　　　　　",//2
					"カナートスの泉、ここはヘラにとって特別な場所である",//3
					"春になるとここでヘラは湯浴みをし、処女性を取り戻す",//4
					"しかし、今はそれどころではないようだ　　　　　　　",//5
					"ヘラ「で、出て行ってくださいまし！！　　　　　　　",//6
					"　　　顔も見たくないと言った筈です！！」　　　　　",//7
					"ゼウス「話を聞いてくれヘラ！！オレが悪かった！！",//8
					"　　　　仲直りしよう！プレゼントがあるんだ！！」",//9
					"ヘラ「ぷれぜんと？？」　　　　　　　　　　　　　",//10
					"そう言うと、ゼウスは雪だるまを手に……　　　　　",//11
					"ゼウス「……ん？」　　　　　　　　　　　　　",//12
					"できなかった　　　　　　　　　　　　　　　　",//13
					"何故なら……　　　　　　　　　　　　　　　　",//14
					"ゼウス「っ…！！」　　　　　　　　　　　　　",//15
					"先ほどの冥界の雷の雨、それに打たれてしまったらしい",//16
					"雪だるまは形を保つ事無く、雪の塊となってボロボロに",//17
					"さらに溶け始めてドロドロになっていたのだ　　　　　",//18
					"ヘラ「……どうしたのです？」　　　　",//19
					"ゼウス「…いや、その…な？？」　　　",//20
					"ゼウスは、仕方なくその雪の塊を見せる",//21
					"ヘラ「……なんですの？それ」　　　　　",//22
					"ゼウス「…スノーマン…だった物だ……」",//23
					"ブ　チ　ン　　　　　　　　　　　　　　　　　",//24
					"という音が聞こえた気がした　　　　　　　　　",//25
					"ヘラ「貴方は…わざわざここまで私をおちょくりに来ましたの！？",//26
					"　　　最低ですわね！！」　　　　　　　　　　　　　　　　　　",//27
					"ゼウス「ち、違うんだヘラ！！これはさっきまでは！！」",//28
					"ヘラ「言い訳は聞きませんわ！！お帰りくださいませ！！　　　　",//29
					"　　　私は湯浴みをしなければいけませんので、さようなら！！」",//30
					"ゼウス「ヘラー！！」　　　　　　　　　　",//31
					"ゼウスのプレゼント作戦は失敗に終わった　　　　　　　　　　",//32
					"ギリシャ神の夫婦喧嘩はまだまだ終わりが見えないようである…",//33
					"　　　　　　　　　　E　N　D　　　　　　　　　　",//34
					"",//36
					"",//37
			};

			str = kotoba[countPush4];
			FontMetrics fm = ginfo.g.getFontMetrics();
			int strw = 0;//fm.stringWidth(str) / 2;
			ginfo.g.drawString(str, 200 - strw, 400);

			if(ginfo.keystate[KEY_STATE.SPACE] == true){
				countPush4 = countPush4 + 1;
				try{
					Thread.sleep(300); //0.5秒Sleepする
					}catch(InterruptedException e){}
			}


			//ローディング画面絵
			if(countPush4 == 0){
				ginfo.g.drawImage(this.img_loading, 0, 0, null);
				str = kotoba[countPush4];
			}else{
			}

			//ゼウス
			if(countPush4 == 1){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_k, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_t, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush4];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ヘラ
			if(countPush4 == 2){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_k, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_t, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush4];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ナレーション
			if(countPush4 == 3){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_k, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_t, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush4];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush4 == 4){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_k, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_t, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush4-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush4];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}
			if(countPush4 == 5){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_k, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_t, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush4-2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush4-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
				str = kotoba[countPush4];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 440);
			}

			//ヘラ
			if(countPush4 == 6){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_d, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush4];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush4 == 7){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_d, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush4-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush4];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//ゼウス
			if(countPush4 == 8){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_d, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush4];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush4 == 9){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_d, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush4-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush4];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//ヘラ
			if(countPush4 == 10){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_s, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush4];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ナレーション
			if(countPush4 == 11){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_s, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush4];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ゼウス
			if(countPush4 == 12){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_s, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush4];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ナレーション
			if(countPush4 == 13){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_s, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush4];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush4 == 14){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_s, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush4-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush4];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//ゼウス
			if(countPush4 == 15){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_s, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush4];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ナレーション
			if(countPush4 == 16){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_s, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush4];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush4 == 17){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_s, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush4-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush4];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}
			if(countPush4 == 18){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_s, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush4-2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush4-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
				str = kotoba[countPush4];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 440);
			}

			//ヘラ
			if(countPush4 == 19){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_s, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush4];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ゼウス
			if(countPush4 == 20){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_s, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush4];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ナレーション
			if(countPush4 == 21){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_s, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush4];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ヘラ
			if(countPush4 == 22){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_s, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush4];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ゼウス
			if(countPush4 == 23){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_s, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush4];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ナレーション
			if(countPush4 == 24){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_d, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush4];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush4 == 25){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_d, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush4-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush4];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//ヘラ
			if(countPush4 == 26){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_d, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush4];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush4 == 27){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_d, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush4-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush4];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//ゼウス
			if(countPush4 == 28){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_d, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush4];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ヘラ
			if(countPush4 == 29){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_d, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush4];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush4 == 30){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_d, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush4-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush4];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//ゼウス
			if(countPush4 == 31){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush4];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ナレーション
			if(countPush4 == 32){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_d, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush4];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush4 == 33){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_d, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush4-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush4];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//END
			if(countPush4 == 34){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_d, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush4];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 400 - strw, 400);
			}

		}

		@Override
		public void loadMedia() throws IOException {
			this.img_back1 = ImageIO.read(new File("gazou/lake000.jpg"));//湖
			this.img_loading = ImageIO.read(new File("gazou/loading.jpg"));
			this.img_waku = ImageIO.read(new File("gazou/ant1.png"));

			this.img_zeu_a = ImageIO.read(new File("zeus/zeusu_a.png"));//ゼウス画像
			this.img_zeu_k = ImageIO.read(new File("zeus/zeusu_k.png"));
			this.img_zeu_s = ImageIO.read(new File("zeus/zeusu_s.png"));


			this.img_hera_d = ImageIO.read(new File("hera/hera_d.png"));//ヘラ画像
			this.img_hera_s = ImageIO.read(new File("hera/hera_s.png"));
			this.img_hera_t = ImageIO.read(new File("hera/hera_t.png"));

		}
	}





	//ハッピーエンド
	class MyGameHappy_end extends GameDisplay{
		private BufferedImage img_back1; //湖
		private BufferedImage img_hera1; //ヘラ吃驚
		private BufferedImage img_hera2; //ヘラ笑顔
		private BufferedImage img_loading; //ローディング仮想画像
		private BufferedImage img_waku; //台詞枠

		private BufferedImage img_zeu_a; //ゼウス（呆れ、焦り）
		private BufferedImage img_zeu_k; //（笑顔）
		private BufferedImage img_zeu_s; //（素）

		private BufferedImage img_hera_d; //ヘラ（怒り）
		private BufferedImage img_hera_k; //（笑顔）
		private BufferedImage img_hera_s; //（素）
		private BufferedImage img_hera_t; //（照れ）

		@Override
		public void show(GraphicsInfo ginfo) {
			String str;

			//台詞代入
			String kotoba[]={"",//0
					"ゼウス「ヘラ！！」",//1
					"ヘラ「！！？？　あなた！！？？」",//2
					"カナートスの泉、ここはヘラにとって特別な場所である",//3
					"春になるとここでヘラは湯浴みをし、処女性を取り戻す",//4
					"しかし、今はそれどころではないようだ　　　　　　　",//5
					"ヘラ「で、出て行ってくださいまし！！　　",//6
					"　　　顔も見たくないと言った筈です！！」",//7
					"ゼウス「話を聞いてくれヘラ！！オレが悪かった！！",//8
					"　　　　仲直りしよう！プレゼントがあるんだ！！」",//9
					"ヘラ「ぷれぜんと？？」",//10
					"そう言うと、ゼウスは雪だるまを手に……",//11
					"ゼウス「……ん？」",//12
					"冥界の雷からは守れたので、形はそこそこ残っていたのだが、",//13
					"雪だるまは、少々溶けて小ぶりになってしまっていた　　　　",//14
					"ヘラ「…どうかしましたの？」",//15
					"ゼウス「……ごめんな、ヘラ」",//16
					"そう言って、ゼウスは溶けかけたスノーマンを取り出した",//17
					"ヘラ「…！！　これって…スノーマン！？」",//18
					"ゼウス「君が昔、見たいと言っていたそうだね　　　　",//19
					"　　　　溶けてしまって不格好だが、私の手作りだ！」",//20
					"ヘラ「貴方様の！？わざわざ作ってくださったのですか！？」",//21
					"そのスノーマンにヘラは驚いてくれ、そして",//22
					"笑ってくれた　　　　　　　　　　　　　　",//23
					"ヘラ「…ありがとうございます、あなた」",//24
					"ゼウス「いや、本当に悪かった、　　　　　　　　　",//25
					"　　　　君を悲しませるような事をしてしまったね」",//26
					"ヘラ「……浮気について、許すつもりはございません",//27
					"　　　しかし、貴方を手放すこともいたしません…」",//28
					"しばらく黙って、ヘラはハデスの顔を見た",//29
					"何とも恥ずかしそうな、乙女の顔で　　　",//30
					"ヘラ「そこで、待っていていただけますか？」",//31
					"ゼウス「え？あ、ああ…」",//32
					"そうゼウスが答えると、ヘラはスノーマンを大事に抱え",//33
					"一人泉へ向かった",//34
					"",//35
					"ヘラ「あなた様」　　　　　",//36
					"ゼウス「…！！」　　　　　",//37
					"沐浴し、処女性を取り戻した美しいヘラの姿に見とれ",//38
					"ゼウスは息を飲んだ　　　　　　　　　　　　　　　",//39
					"ヘラ「重ねて言います、浮気を許す訳ではありません　　",//40
					"　　　……しかし、もし浮気をまたしてしまったとしても",//41
					"　　　ちゃんと私の所に帰ってきていただけますか？　　",//42
					"ゼウス「！！　もちろん！！決まっているだろう！！」",//43
					"かくして、ゼウスの子どもたちの暗躍（？）もあり",//44
					"神々の夫婦喧嘩は幕を閉じた　　　　　　　　　　",//45
					"　　　　　　E　N　D　　　　　",//46
					"",//47
					"",//48
			};

			str = kotoba[countPush5];
			FontMetrics fm = ginfo.g.getFontMetrics();
			int strw = 0;//fm.stringWidth(str) / 2;
			ginfo.g.drawString(str, 200 - strw, 400);

			if(ginfo.keystate[KEY_STATE.SPACE] == true){
				countPush5 = countPush5 + 1;
				try{
					Thread.sleep(300); //0.5秒Sleepする
					}catch(InterruptedException e){}
			}


			//ローディング画面絵
			if(countPush5 == 0){
				ginfo.g.drawImage(this.img_loading, 0, 0, null);
				str = kotoba[countPush5];
			}else{
			}

			//ゼウス、ヘラ
			if((countPush5 == 1) || (countPush5 == 2)){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_k, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_t, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush5];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ナレーション
			if(countPush5 == 3){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_k, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_t, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush5];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush5 == 4){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_k, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_t, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush5-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush5];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}
			if(countPush5 == 5){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_k, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_t, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush5-2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush5-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
				str = kotoba[countPush5];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 440);
			}

			//ヘラ
			if(countPush5 == 6){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_k, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_d, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush5];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush5 == 7){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_k, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_d, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush5-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush5];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//ゼウス
			if(countPush5 == 8){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_d, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush5];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush5 == 9){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_d, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush5-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush5];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//ヘラ
			if(countPush5 == 10){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_s, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush5];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ナレーション
			if(countPush5 == 11){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_s, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush5];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ゼウス
			if(countPush5 == 12){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_s, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush5];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ナレーション
			if(countPush5 == 13){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_s, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush5];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush5 == 14){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_s, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush5-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush5];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//ヘラ、ゼウス、ナレーション
			if((countPush5 == 15) || (countPush5 == 16) || (countPush5 == 17)){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_s, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush5];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ヘラ
			if(countPush5 == 18){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_t, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush5];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ゼウス
			if(countPush5 == 19){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_t, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush5];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush5 == 20){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_t, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush5-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush5];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//ヘラ
			if(countPush5 == 21){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_a, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_t, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush5];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ナレーション
			if(countPush5 == 22){
				ginfo.g.drawImage(this.img_hera1, 0, 0, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush5];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush5 == 23){
				ginfo.g.drawImage(this.img_hera2, 0, 0, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush5-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush5];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//ヘラ
			if(countPush5 == 24){
				ginfo.g.drawImage(this.img_hera2, 0, 0, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush5];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ゼウス
			if(countPush5 == 25){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_k, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush5];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush5 == 26){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_k, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush5-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush5];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//ヘラ
			if(countPush5 == 27){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_s, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush5];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush5 == 28){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_s, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush5-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush5];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//ナレーション
			if(countPush5 == 29){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_t, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush5];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush5 == 30){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_t, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush5-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush5];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//ヘラ
			if(countPush5 == 31){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_t, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush5];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ゼウス
			if(countPush5 == 32){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_t, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush5];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ナレーション
			if(countPush5 == 33){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_t, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush5];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush5 == 34){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_t, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush5-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush5];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//ローディング画面絵
			if(countPush5 == 35){
				ginfo.g.drawImage(this.img_loading, 0, 0, null);
				str = kotoba[countPush5];
			}

			//ヘラ
			if(countPush5 == 36){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_t, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush5];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ゼウス
			if(countPush5 == 37){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_t, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush5];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ナレーション
			if(countPush5 == 38){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_t, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush5];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush5 == 39){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_t, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush5-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush5];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//ヘラ
			if(countPush5 == 40){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_t, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush5];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush5 == 41){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_t, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush5-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush5];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}
			if(countPush5 == 42){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_t, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush5-2];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush5-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
				str = kotoba[countPush5];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 440);
			}

			//ゼウス
			if(countPush5 == 43){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_t, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush5];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}

			//ナレーション
			if(countPush5 == 44){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_t, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush5];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
			}
			if(countPush5 == 45){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_t, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush5-1];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 400);
				str = kotoba[countPush5];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 380 - strw, 420);
			}

			//ナレーション
			if(countPush5 == 46){
				ginfo.g.drawImage(this.img_back1, 0, 0, null);
				ginfo.g.drawImage(this.img_zeu_s, 20, 80, null);
				ginfo.g.drawImage(this.img_hera_t, 400, 80, null);
				ginfo.g.drawImage(this.img_waku, 100, 350, null);
				ginfo.g.setColor(Color.WHITE);
				ginfo.g.setFont(MyGameDisplay.this.mfont2);

				str = kotoba[countPush5];
				fm = ginfo.g.getFontMetrics();
				strw = fm.stringWidth(str) / 2;
				ginfo.g.drawString(str, 400 - strw, 400);
			}


		}

		@Override
		public void loadMedia() throws IOException {
			this.img_back1 = ImageIO.read(new File("gazou/lake000.jpg"));
			this.img_hera1 = ImageIO.read(new File("gazou/hera1.jpg"));
			this.img_hera2 = ImageIO.read(new File("gazou/hera2.jpg"));
			this.img_loading = ImageIO.read(new File("gazou/loading2.jpg"));
			this.img_waku = ImageIO.read(new File("gazou/ant1.png"));

			this.img_zeu_a = ImageIO.read(new File("zeus/zeusu_a.png"));//ゼウス画像
			this.img_zeu_k = ImageIO.read(new File("zeus/zeusu_k.png"));
			this.img_zeu_s = ImageIO.read(new File("zeus/zeusu_s.png"));


			this.img_hera_d = ImageIO.read(new File("hera/hera_d.png"));//ヘラ画像
			this.img_hera_k = ImageIO.read(new File("hera/hera_k.png"));
			this.img_hera_s = ImageIO.read(new File("hera/hera_s.png"));
			this.img_hera_t = ImageIO.read(new File("hera/hera_t.png"));
		}

	}



}