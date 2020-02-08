import numpy as np # 1.16.4
import pandas as pd # 0.24.2
import matplotlib # 2.2.4
#Python 2.7
import matplotlib.pyplot as plt


def sigmoid(x):
    return 1 / (1 + np.exp(-0.005*x))

def sigmoid_derivative(x):
    return 0.005 * x * (1 - x )

def read_and_divide_into_train_and_test(csv_file):
    

    df=pd.read_csv(csv_file).astype(str)
    row, column = df.shape
    #print("---------------------------------11111111111-----------------------\n",df)

    df.replace('?', '0', inplace = True)

    df=df.astype(float)
    #print("---------------------------------2222222222222222-----------------------\n",df)
   
    #train_df   --->>  Train data
    
    training_inputs=df.iloc[  :int(row*4/5),:]
    #train_df   --->>  Test data
    test_inputs=df.iloc[ int(row*4/5):,:]

    training_labels=list(training_inputs['Class'])

    test_labels=list(test_inputs['Class'])
    

    attributes=df.columns.tolist() 

    
    
    
    plt.matshow(training_inputs.corr())
    plt.xticks(range(len(training_inputs.columns)), training_inputs.columns ,rotation=60)
    plt.yticks(range(len(training_inputs.columns)), training_inputs.columns,rotation=60)
    plt.colorbar()
    plt.show()
    
    
 
    return training_inputs, training_labels, test_inputs, test_labels ,attributes



def run_on_test_set(test_inputs, test_labels, weights):
    tp = 0
    #calculate test_predictions
    #TODO map each prediction into either 0 or 1
    test_outputs = sigmoid ( np.dot(test_inputs,weights)) 


    test_predictions=[]
    for a in test_outputs:
        if a[0]>0.5:
            test_predictions.append(1)
        else:
            test_predictions.append(0)

    for predicted_val, label in zip(test_predictions, test_labels):
        if predicted_val == label:
            tp += 1
    accuracy = tp / len(test_predictions)
    return accuracy


def plot_loss_accuracy(accuracy_array, loss_array):
  
    plt.ylabel('Accuracy')
    plt.xlabel('Iteration')
    plt.plot(accuracy_array,label="Test")
    plt.legend()
    plt.show()

    plt.ylabel('loss')
    plt.xlabel('Iteration')
    plt.plot(loss_array,label="Train")
    plt.legend()
    plt.show()
def main():
    csv_file = './breastcancerwisconsin.csv'
    iteration_count = 2500
    np.random.seed(1)
    weights = 2 * np.random.random((9, 1)) - 1
    accuracy_array = []
    loss_array = []
    training_inputs, training_labels, test_inputs, test_labels, attributes = read_and_divide_into_train_and_test(csv_file)


   
    training_inputs=training_inputs.astype(float)



    training_inputs=training_inputs[attributes[1:len(attributes)-1]].to_numpy()
    test_inputs=test_inputs[attributes[1:len(attributes)-1]].to_numpy()
    training_outputs=np.array(training_labels)
    training_outputs=np.reshape(training_outputs, (-1, 1))
    
   
    accuracy_array = []
    loss_array = []


    xx=[]
    for iteration in range(iteration_count):
        
        outputs =np.dot(training_inputs,weights)
        outputs = sigmoid (outputs)

            
        loss = training_outputs-outputs 
        tuning = loss * sigmoid_derivative(outputs)
       
        #print("--------------------------------- -",outputs.shape)
        weights += np.dot(np.transpose(training_inputs),tuning)
        #calculate outputs
        #calculate loss
        #calculate tuning
        #update weights
        #run_on_test_set
        
        print(loss.mean())
       
        accuracy_array.append(run_on_test_set(test_inputs,test_labels,weights))
        loss_array.append(loss.mean())

    # you are expected to add each accuracy value into accuracy_array
    # you are expected to find mean value of the loss for plotting purposes and add each value into loss_array

    plot_loss_accuracy(accuracy_array, loss_array)

if __name__ == '__main__':
    main()
