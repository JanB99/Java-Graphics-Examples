package shape;

public class Vector3d {

    public float x, y, z;

    public Vector3d(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3d sub(float x, float y, float z){
        this.x -= x;
        this.y -= y;
        this.z -= z;
        return this;
    }

    public Vector3d div(float x, float y, float z){
        this.x /= x;
        this.y /= y;
        this.z /= z;
        return this;
    }

    public Vector3d div(float n){
        this.x /= n;
        this.y /= n;
        this.z /= n;
        return this;
    }

    public Vector3d mult(float n){
        this.x *= n;
        this.y *= n;
        this.z *= n;
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
        return (float) Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    public float magSq(){
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }

    public void limit(float n){
        if (this.magSq() > n * n * n){
            this.div((float) Math.sqrt(this.magSq())).mult(n);
        }
    }
}
