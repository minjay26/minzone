package cn.tendata.minzone.manager.data.enums;

public enum Operation {
    PLUS {
		@Override
		double apply(double a, double b) {
			// TODO Auto-generated method stub
			return a+b;
		}
	},MINUS {
		@Override
		double apply(double a, double b) {
			// TODO Auto-generated method stub
			return a-b;
		}
	},TIMES {
		@Override
		double apply(double a, double b) {
			// TODO Auto-generated method stub
			return a*b;
		}
	},DIVIDE {
		@Override
		double apply(double a, double b) {
			// TODO Auto-generated method stub
			return a/b;
		}
	};
	
	abstract double apply(double a,double b);
}
