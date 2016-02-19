		
		/**
		*广播使用传值非常方便，但一般不建议使用这个，建议使用接口回调，
		* 或者用EventBus框架更简单。
		*/
		//发送
		Intent intent = new Intent("com.trilink.artapp.newStudentCount");  
		intent.putExtra("newStudentCount", String.valueOf(newStudentCount)); 
		sendBroadcast(intent);  


		//接收
		receiver = new MyReceiver();  
		IntentFilter filter = new IntentFilter();  
		filter.addAction("com.trilink.artapp.newStudentCount");  		          
		getActivity().registerReceiver(receiver, filter); 

		class MyReceiver extends BroadcastReceiver {  
				@Override  
				public void onReceive(Context context, Intent intent) {  
					String newStudentCount=intent.getStringExtra("newStudentCount");
					int newStudentNum=Integer.parseInt(newStudentCount);
					if(newStudentNum>0){
						backgroundDefaultBadge.setText(newStudentNum+""); 
						backgroundDefaultBadge.setTextSize(12);
						backgroundDefaultBadge.setBadgePosition(BadgeView.POSITION_TOP_RIGHT); //默认值
						backgroundDefaultBadge.setBadgeMargin(5, 5);
						backgroundDefaultBadge.show();
					}else{
						backgroundDefaultBadge.hide();
					}
				}  

			}  