using UnityEngine;
using System.Collections;

public class EggCollider : MonoBehaviour {



    PlayerScript myPlayerScript;


   

   public string lasttag;




    GameObject obj;
    public int combo;
    void Awake()
    {


       
        obj = GameObject.FindGameObjectWithTag("bucket");


        lasttag ="null";
        combo=0; 
        myPlayerScript = transform.parent.GetComponent<PlayerScript>();
       
    }


    
    void Update()
    {
         

    }

    //Triggered by Unity's Physics
	void OnTriggerEnter(Collider theCollision)
    {

        if (!obj.GetComponent<PlayerScript>().gameover)
        {



            GameObject collisionGO = theCollision.gameObject;




            //If the GameObject has the same tag as specified, output this message in the console



            obj.GetComponent<PlayerScript>().sounds[1].Play();




            if (string.Compare(lasttag, collisionGO.gameObject.tag) == 0)
            {
                combo++;

                obj.GetComponent<PlayerScript>().combopanel.SetActive(true);

                /// Instantiate(combotext, new Vector3(3,5,0), Quaternion.identity);
                /// 

                
                obj.GetComponent<PlayerScript>().combotext.text = "Comboo +" + combo.ToString() + " ";

            }
            else
            {
                combo = 0;
                obj.GetComponent<PlayerScript>().combopanel.SetActive(false);

            }

            



            Uplive(collisionGO);




            lasttag = collisionGO.gameObject.tag;
            Destroy(collisionGO);


            if (!myPlayerScript.gameover)
            {

                myPlayerScript.theScore = myPlayerScript.theScore + 1 + IntPow(combo, 2);
            }
        }
      
    }
    int IntPow(int x, uint pow)
    {
        int ret = 1;
        while (pow != 0)
        {
            if ((pow & 1) == 1)
                ret *= x;
            x *= x;
            pow >>= 1;
        }
        return ret;
    }
    void Uplive(GameObject  egg){




        switch (egg.gameObject.tag)
        {


            case  "redegg":
                myPlayerScript.Currenthealth = myPlayerScript.Currenthealth + 20;

                obj.GetComponent<PlayerScript>().combotext.color = new Color(222, 0, 0);
                break;
            case "greenegg":
                myPlayerScript.Currentmultipower = myPlayerScript.Currentmultipower + 10;
                obj.GetComponent<PlayerScript>().combotext.color = new Color(0, 222, 0);
                break;
            case "blueegg":
                myPlayerScript.Currenthugepower = myPlayerScript.Currenthugepower + 5;
                obj.GetComponent<PlayerScript>().combotext.color = new Color(0, 0, 222);
                break;
            case "yellowegg":
                myPlayerScript.Currenthealth = myPlayerScript.Maximunthealth*3/4 ;
              ///  obj.GetComponent<PlayerScript>().combotext.color = new Color(222, 222, 10);
                break;
            default:

                break;
        }

       
    }
}
