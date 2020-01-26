package shape;

public class Vector2d {

    public float x, y;

    public Vector2d(float x, float y){
        this.x = x;
        this.y = y;
    }

    public Vector2d sub(float x, float y){
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vector2d div(float x, float y){
        this.x /= x;
        this.y /= y;
        return this;
    }

    public Vector2d div(float n){
        this.x /= n;
        this.y /= n;
        return this;
    }

    public Vector2d mult(float n){
        this.x *= n;
        this.y *= n;
        return this;
    }

    public void setMag(float n){
        this.norm();
        this.mult(n);
    }

    public void norm(){
        this.div(this.mag());
    }

    public float mag(){
        return (float) Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public float magSq(){
        return this.x * this.x + this.y * this.y;
    }

    public void limit(float n){
        if (this.magSq() > n * n){
            this.div((float) Math.sqrt(this.magSq())).mult(n);
        }
    }
}
