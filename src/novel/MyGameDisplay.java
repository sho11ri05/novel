package novel;

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

	public MyGameDisplay(){
		this.title = new MyGameTitle();
		this.start = new MyGameStart();
		this.center = new MyGameCenter();
		this.center2 = new MyGameCenter2();
		this.bad_end = new MyGameBad_end();
		this.happy_end = new MyGameHappy_end();
		GameDisplay.current = this.title;
	}

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
		private BufferedImage img_zeu_d; //（怒り）
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
					"何度も何度も浮気を重ねているのである。　　　　　　　　　　",//21
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
			if(countPush == 73){
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

			//ゼウス、アルテミス
			if((countPush == 74) || (countPush == 75)){
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

			//ナレーション
			if(countPush == 87){
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
			if(countPush == 88){
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


			//ローディング画面絵
			if(countPush == 89){
				ginfo.g.drawImage(this.img_loading, 0, 0, null);
				str = kotoba[countPush];
			}else{
			}

			if(countPush == 90){
				GameDisplay.current = MyGameDisplay.this.center;
			}else{
			}


//			if(ginfo.keystate[KEY_STATE.SPACE] == true){
//				GameDisplay.current = MyGameDisplay.this.center;
//			}

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
			this.img_zeu_d = ImageIO.read(new File("zeus/zeusu_d.png"));
			this.img_zeu_k = ImageIO.read(new File("zeus/zeusu_k.png"));
			this.img_zeu_s = ImageIO.read(new File("zeus/zeusu_s.png"));


			this.img_hera_d = ImageIO.read(new File("hera/hera_d.png"));//ヘラ画像
			this.img_hera_k = ImageIO.read(new File("hera/hera_k.png"));
			this.img_hera_s = ImageIO.read(new File("hera/hera_s.png"));
			this.img_hera_t = ImageIO.read(new File("hera/hera_t.png"));
		}
	}







	//中間地点
	class MyGameCenter extends GameDisplay{
		private BufferedImage img_back1; //雪背景
		private BufferedImage img_back2; //冥界背景
		private BufferedImage img_loading; //ローディング仮想画像２
		private BufferedImage img_waku; //台詞枠

		private BufferedImage img_zeu_a; //ゼウス（呆れ、焦り）
		private BufferedImage img_zeu_d; //（怒り）
		private BufferedImage img_zeu_k; //（笑顔）
		private BufferedImage img_zeu_s; //（素）

		private BufferedImage img_hade_a; //ハデス（呆れ）
		private BufferedImage img_hade_s; //（素）

/**
		private BufferedImage img_heru_s; //ヘルメス（素）
		private BufferedImage img_heru_a; //
		**/

		@Override
		public void show(GraphicsInfo ginfo) {
			String str;

			//台詞代入
			String kotoba[]={"",//0
					"ゼウス「よし、このくらいでいいだろう！」",//1
					"空から降ってきた綺麗な雪で、雪だるまを作っていたゼウスに",//2
					"天から一人の神が舞い降りてきた",//3
					"？？？「父上様、大変でございます」",//4
					"ゼウス「ん？どうしたヘルメスよ？」",//5
					"旅人の神、商売の神等の呼び名があるヘルメス、彼もゼウスの子である",//6
					"母はヘラではないが、彼はヘラに我が子の様に育てられている　　　　",//7
					"頭が良く、ゼウスの伝令役として各地を飛んでいる神でもある　　　　",//8
					"ヘルメス「先刻、この様な手紙を叔父上様より預かってきました」",//9
					"ゼウス「ん？ハデスから手紙？…珍しい」",//10
					"そう言って、ヘルメスから手紙を受け取ったゼウスは、",//11
					"手紙を読んでからヘルメスを見る　　　　　　　　　　",//12
					"ゼウス「…どういうことだ？ハデスがヘラを返さないだと…？」　　　",//13
					"ヘルメス「左様で御座います、ペルセポネ様もその意思のご様子で…」",//14
					"ゼウス「ふざけるな！そんなの許せる訳ないだろう！！」",//15
					"そう言うと、風の様にゼウスは消え去ってしまった",//16
					"残ったヘルメスはというと…　　　　　　　　　　",//17
					"ヘルメス「…やれやれ、困った父上様と母上様だな…　　　　",//18
					"　　　　　息子と娘がこうも手を焼かないといけないとは…」",//19
					"消え去ったゼウスの後を見て、怪しく笑うヘルメスを",//20
					"ゼウスは知らない　　　　　　　　　　　　　　　　",//21
					"",//22
					"ゼウス「ハデス！ハーデース！！」",//23
					"？？？「……煩いぞ、末弟よ」",//24
					"冥界、死者の国にやってきたゼウスは、崖の対岸に彼を呼び出した",//25
					"彼はハデス。絶対なる冥界の王であり、　　　　　　　　　　　　",//26
					"ヘラの弟、そしてゼウスの兄でもある　　　　　　　　　　　　　",//27
					"ゼウス「煩いじゃねぇよ！！何だこの手紙は！！」",//28
					"ハデス「……手紙？……はて、何の事だ？」",//29
					"ゼウス「とぼけんな！！いいから橋を出せ！！」",//30
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


		}

		@Override
		public void loadMedia() throws IOException {
			this.img_back1 = ImageIO.read(new File("gazou/hiroi2-2.jpg"));//雪背景
			this.img_back2 = ImageIO.read(new File("gazou/zyouge_main.png"));//冥界背景
			this.img_loading = ImageIO.read(new File("gazou/loading2.jpg"));//ローディング画像２
			this.img_waku = ImageIO.read(new File("gazou/ant1.png"));

			this.img_zeu_a = ImageIO.read(new File("zeus/zeusu_a.png"));//ゼウス画像
			this.img_zeu_d = ImageIO.read(new File("zeus/zeusu_d.png"));
			this.img_zeu_k = ImageIO.read(new File("zeus/zeusu_k.png"));
			this.img_zeu_s = ImageIO.read(new File("zeus/zeusu_s.png"));

			this.img_hade_a = ImageIO.read(new File("hadesu/hadesu_a.png"));//ハデス画像
			this.img_hade_s = ImageIO.read(new File("hadesu/hadesu_s.png"));
/**
			this.img_heru_k = ImageIO.read(new File(""));//ヘルメス画像
			this.img_heru_s = ImageIO.read(new File(""));
**/



		}

	}




	//中間２
	class MyGameCenter2 extends GameDisplay{
		private BufferedImage img_back1; //雪背景
		private BufferedImage img_back2; //冥界背景
		private BufferedImage img_loading; //ローディング仮想画像２
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
					"",//1
					"",//2
					"",//3
					"",//4
					"",//5
					"",//6
					"",//7
					"",//8
					"",//9
					"",//10
					"",//11
					"",//12
					"",//13
					"",//14
					"",//15
					"",//16
					"",//17
					"",//18
					"",//19
					"",//20
					"",//21
					"",//22
					"",//23
					"",//24
					"",//25
					"",//26
					"",//27
					"",//28
					"",//29
					"",//30
					"",//31
					"",//32
					"",//33
					"",//34
					"",//35
					"",//36
					"",//37
					"",//38
					"",//39
					"",//40
					"",//41
					"",//42
					"",//43
					"",//44
					"",//45
					"",//46
					"",//47
					"",//48
					"",//49
					"",//50
					"",//51
					"",//52
					"",//53
					"",//54
					"",//55
					"",//56
					"",//57
					"",//58
					"",//59
					"",//60
					"",//61
					"",//62
					"",//63
					"",//64
					"",//65
					"",//66
					"",//67
					"",//68
					"",//69
					"",//70
					"",//71
					"",//72
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
			}

		@Override
		public void loadMedia() throws IOException {
			this.img_back1 = ImageIO.read(new File("gazou/hiroi2-2.jpg"));//雪背景
			this.img_back2 = ImageIO.read(new File("gazou/zyouge_main.png"));//冥界背景
			this.img_loading = ImageIO.read(new File("gazou/loading2.jpg"));//ローディング画像２
			this.img_waku = ImageIO.read(new File("gazou/ant1.png"));

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
		private BufferedImage img_back1; //神殿
		private BufferedImage img_back2; //序章
		private BufferedImage img_loading; //ローディング仮想画像
		private BufferedImage img_waku; //台詞枠

		private BufferedImage img_zeu_a; //ゼウス（呆れ、焦り）
		private BufferedImage img_zeu_d; //（怒り）
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
					"",//1
					"",//2
					"",//3
					"",//4
					"",//5
					"",//6
					"",//7
					"",//8
					"",//9
					"",//10
					"",//11
					"",//12
					"",//13
					"",//14
					"",//15
					"",//16
					"",//17
					"",//18
					"",//19
					"",//20
					"",//21
					"",//22
					"",//23
					"",//24
					"",//25
					"",//26
					"",//27
					"",//28
					"",//29
					"",//30
					"",//31
					"",//32
					"",//33
					"",//34
					"",//35
					"",//36
					"",//37
					"",//38
					"",//39
					"",//40
					"",//41
					"",//42
					"",//43
					"",//44
					"",//45
					"",//46
					"",//47
					"",//48
					"",//49
					"",//50
					"",//51
					"",//52
					"",//53
					"",//54
					"",//55
					"",//56
					"",//57
					"",//58
					"",//59
					"",//60
					"",//61
					"",//62
					"",//63
					"",//64
					"",//65
					"",//66
					"",//67
					"",//68
					"",//69
					"",//70
					"",//71
					"",//72
					"",//73
					"",//74
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
		}

		@Override
		public void loadMedia() throws IOException {
			this.img_back1 = ImageIO.read(new File("gazou/rouka00.jpg"));
			this.img_back2 = ImageIO.read(new File("gazou/sky2.png"));
			this.img_loading = ImageIO.read(new File("gazou/loading.jpg"));
			this.img_waku = ImageIO.read(new File("gazou/ant1.png"));

			this.img_zeu_a = ImageIO.read(new File("zeus/zeusu_a.png"));//ゼウス画像
			this.img_zeu_d = ImageIO.read(new File("zeus/zeusu_d.png"));
			this.img_zeu_k = ImageIO.read(new File("zeus/zeusu_k.png"));
			this.img_zeu_s = ImageIO.read(new File("zeus/zeusu_s.png"));


			this.img_hera_d = ImageIO.read(new File("hera/hera_d.png"));//ヘラ画像
			this.img_hera_k = ImageIO.read(new File("hera/hera_k.png"));
			this.img_hera_s = ImageIO.read(new File("hera/hera_s.png"));
			this.img_hera_t = ImageIO.read(new File("hera/hera_t.png"));

		}
	}





	//ハッピーエンド
	class MyGameHappy_end extends GameDisplay{
		private BufferedImage img_back1; //神殿
		private BufferedImage img_back2; //序章
		private BufferedImage img_loading; //ローディング仮想画像
		private BufferedImage img_waku; //台詞枠

		private BufferedImage img_zeu_a; //ゼウス（呆れ、焦り）
		private BufferedImage img_zeu_d; //（怒り）
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
					"",//1
					"",//2
					"",//3
					"",//4
					"",//5
					"",//6
					"",//7
					"",//8
					"",//9
					"",//10
					"",//11
					"",//12
					"",//13
					"",//14
					"",//15
					"",//16
					"",//17
					"",//18
					"",//19
					"",//20
					"",//21
					"",//22
					"",//23
					"",//24
					"",//25
					"",//26
					"",//27
					"",//28
					"",//29
					"",//30
					"",//31
					"",//32
					"",//33
					"",//34
					"",//35
					"",//36
					"",//37
					"",//38
					"",//39
					"",//40
					"",//41
					"",//42
					"",//43
					"",//44
					"",//45
					"",//46
					"",//47
					"",//48
					"",//49
					"",//50
					"",//51
					"",//52
					"",//53
					"",//54
					"",//55
					"",//56
					"",//57
					"",//58
					"",//59
					"",//60
					"",//61
					"",//62
					"",//63
					"",//64
					"",//65
					"",//66
					"",//67
					"",//68
					"",//69
					"",//70
					"",//71
					"",//72
					"",//73
					"",//74
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
		}

		@Override
		public void loadMedia() throws IOException {
			this.img_back1 = ImageIO.read(new File("gazou/rouka00.jpg"));
			this.img_back2 = ImageIO.read(new File("gazou/sky2.png"));
			this.img_loading = ImageIO.read(new File("gazou/loading.jpg"));
			this.img_waku = ImageIO.read(new File("gazou/ant1.png"));

			this.img_zeu_a = ImageIO.read(new File("zeus/zeusu_a.png"));//ゼウス画像
			this.img_zeu_d = ImageIO.read(new File("zeus/zeusu_d.png"));
			this.img_zeu_k = ImageIO.read(new File("zeus/zeusu_k.png"));
			this.img_zeu_s = ImageIO.read(new File("zeus/zeusu_s.png"));


			this.img_hera_d = ImageIO.read(new File("hera/hera_d.png"));//ヘラ画像
			this.img_hera_k = ImageIO.read(new File("hera/hera_k.png"));
			this.img_hera_s = ImageIO.read(new File("hera/hera_s.png"));
			this.img_hera_t = ImageIO.read(new File("hera/hera_t.png"));
		}

	}



}