using UnityEngine;
using System.Collections;
 using System.Collections.Generic;

public class SpawnerScript : MonoBehaviour {

    public Transform eggPrefab;

    private float inittime ;
    private float passedtime ;





  ///  private bool eggday = false;

    public GameObject[]  eggs=new GameObject[4];

    public  List<GameObject> all_eggs = new List<GameObject>();

    public float gamelevel;

    private float levelinit;
    private float levelpassed;

    GameObject obj;
    void Awake()
    {


        

        obj = GameObject.FindGameObjectWithTag("bucket");


       
     
       

    }

    void Start()
     {
        levelinit = Time.time;


        gamelevel = 1.4f;
        inittime =Time.time;
        
    }
	void Update () {

        passedtime=Time.time-inittime;


        

        
        if (passedtime > gamelevel)
        {
            SpawnEgg();
           
           inittime=Time.time;


            levelpassed = Time.time - levelinit;
            if (levelpassed>15)
            {
                levelinit = Time.time;
                gamelevel -= 0.1f;

            }
           
            
        }



	}
  
    void SpawnEgg()
    {


        int index; ;
        float addXPos ;
        Vector3 spawnPos ;






        int eggnumber = Random.Range(5,20);

      if (obj.GetComponent<PlayerScript>().eggday)
        {
        
            for (int i = 0; i < eggnumber; i++)
            {
                index = Random.Range(0, 3);
                addXPos = Random.Range(-1.6f, 1.6f);
                float addYPos = Random.Range(0f, 2.0f);
                spawnPos = transform.position + new Vector3(addXPos, addYPos, 0);
                GameObject eg = (GameObject)Instantiate(eggs[index], spawnPos, Quaternion.identity);
                all_eggs.Add(eg);
                obj.GetComponent<PlayerScript>().eggday = false;

            }

        }
      
      

        index = Random.Range(0, 4);
        addXPos = Random.Range(-1.6f, 1.6f);
        spawnPos = transform.position + new Vector3(addXPos, 0, 0);
        GameObject egg= (GameObject)  Instantiate(eggs[index], spawnPos, Quaternion.identity);
        all_eggs.Add(egg);


        if(all_eggs.Count>30){

            

          
                    
                    Destroy( (GameObject)  all_eggs[0]);
                    all_eggs.RemoveAt(0);

          
           
        }
       /// print("array size -->> "+all_eggs.Count);
      
    }
}
