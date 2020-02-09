using System.Collections;
using UnityEngine.UI;

using UnityEngine;

public class Replaybutton : MonoBehaviour
{
    // Start is called before the first frame update


    GameObject obj;
    GameObject obj0;
        GameObject obj1;
    public Button m_YourFirstButton;

    void Start()
    {


        obj = GameObject.FindGameObjectWithTag("bucket");
        obj0 = GameObject.FindGameObjectWithTag("spawn");
        obj1 = GameObject.FindGameObjectWithTag("collider");
        m_YourFirstButton.onClick.AddListener(TaskOnClick);
       
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }



    
     void TaskOnClick() {

        obj.GetComponent<PlayerScript>().sounds[2].Play();
        obj.GetComponent<PlayerScript>().gameOverPanel.SetActive(false);
        obj.GetComponent<PlayerScript>().theScore = 0;


        obj.GetComponent<PlayerScript>().Currenthealth = obj.GetComponent<PlayerScript>().Maximunthealth;
        obj.GetComponent<PlayerScript>().Currentmultipower = 0;
        obj.GetComponent<PlayerScript>().gameover = false;
        obj.GetComponent<PlayerScript>().combopanel.SetActive(false);
             obj.GetComponent<PlayerScript>().youwin.SetActive(false);
        obj1.GetComponent<EggCollider>().combo = 0;
        obj1.GetComponent<EggCollider>().lasttag = "null";

        obj.GetComponent<PlayerScript>().Currenthugepower = 0;
        obj0.GetComponent<SpawnerScript>().gamelevel = 1.5f;
        for (int i=0;i < obj0.GetComponent<SpawnerScript>().all_eggs.Count;i++) {


            DestroyImmediate( (GameObject)  obj0.GetComponent<SpawnerScript>().all_eggs[i]);
           
        }
         
       
    }
}
